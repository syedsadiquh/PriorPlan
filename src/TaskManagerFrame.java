import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;

public class TaskManagerFrame extends JFrame {
    private JPanel tasksPanel;
    private DatabaseConnector connector;

    public TaskManagerFrame() {
        setTitle("Task Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        connector = new DatabaseConnector();

        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(tasksPanel);
        add(scrollPane, BorderLayout.CENTER);

        displayPendingTasks();

        setVisible(true);
    }

    private void displayPendingTasks() {
        try {
            PreparedStatement statement = connector.prepareStatement("SELECT * FROM tasks WHERE done = false");
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String dueDate = resultSet.getString("due_date");
                String priority = resultSet.getString("priority");
                int taskId = resultSet.getInt("task_id");
    
                JPanel taskPanel = createTaskPanel(description, dueDate, priority, taskId);
                tasksPanel.add(taskPanel);
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching tasks: " + ex.getMessage());
        }
    }
    
    private JPanel createTaskPanel(String description, String dueDate, String priority, int taskId) {
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    
        JLabel descriptionLabel = new JLabel("Description: " + description);
        taskPanel.add(descriptionLabel);
    
        JButton updateButton = createUpdateButton(description, dueDate, priority, taskId);
        taskPanel.add(updateButton);
    
        JButton deleteButton = createDeleteButton(taskId);
        taskPanel.add(deleteButton);
    
        JButton completeButton = createCompleteButton(taskId);
        taskPanel.add(completeButton);
    
        return taskPanel;
    }
    
    private JButton createUpdateButton(String description, String dueDate, String priority, int taskId) {
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            // Create a dialog box for updating task details
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
   
            JTextField descriptionField = new JTextField(description);
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
   
            JTextField dueDateField = new JTextField(dueDate);
            panel.add(new JLabel("Due Date:"));
            panel.add(dueDateField);
   
            JComboBox<String> priorityComboBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
            priorityComboBox.setSelectedItem(priority);
            panel.add(new JLabel("Priority:"));
            panel.add(priorityComboBox);
   
            int result = JOptionPane.showConfirmDialog(null, panel, "Update Task",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
   
            if (result == JOptionPane.OK_OPTION) {
                String newDescription = descriptionField.getText();
                String newDueDate = dueDateField.getText();
                String newPriority = (String) priorityComboBox.getSelectedItem();
   
                // Update the task in the database
                updateTask(taskId, newDescription, newDueDate, newPriority);
            }
        });
        return updateButton;
    }
    
    private JButton createDeleteButton(int taskId) {
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            try {
                String description = getDescription(taskId);
                
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the task: " + description, "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                        
                if (option == JOptionPane.YES_OPTION) {
                    deleteTask(taskId);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting task: " + ex.getMessage());
            }
        });
        return deleteButton;
    }
    
    private JButton createCompleteButton(int taskId) {
        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            try {
                String description = getDescription(taskId);
                // Mark task as complete
                markTaskAsComplete(taskId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error completing task: " + ex.getMessage());
            }
        });
        return completeButton;
    }

    private void updateTask(int taskId, String newDescription, String newDueDate, String newPriority) {
        try {
            PreparedStatement updateStatement = connector.prepareStatement(
                    "UPDATE tasks SET description = ?, due_date = ?, priority = ? WHERE task_id = ?");
            updateStatement.setString(1, newDescription);
            updateStatement.setString(2, newDueDate);
            updateStatement.setString(3, newPriority);
            updateStatement.setInt(4, taskId);
            int rowsUpdated = updateStatement.executeUpdate();
    
            if (rowsUpdated > 0) {
                // Task updated successfully
                JOptionPane.showMessageDialog(null, "Task updated successfully.");
                // Refresh the task display
                refreshTaskDisplay();
            } else {
                // Task update failed
                JOptionPane.showMessageDialog(null, "Failed to update task.");
            }
            updateStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating task: " + ex.getMessage());
        }
    }
    
    
    private void deleteTask(int taskId) {
        try {
            PreparedStatement deleteStatement = connector.prepareStatement("DELETE FROM tasks WHERE task_id = ?");
            deleteStatement.setInt(1, taskId);
            int rowsDeleted = deleteStatement.executeUpdate();
            
            if (rowsDeleted > 0) {
                // Task deleted successfully
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                // Refresh the task display
                refreshTaskDisplay();
            } else {
                // Task deletion failed
                JOptionPane.showMessageDialog(null, "Failed to delete task.");
            }
            deleteStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting task: " + ex.getMessage());
        }
    }
    

    private void markTaskAsComplete(int taskId) {
        try {
            PreparedStatement markCompleteStatement = connector.prepareStatement("UPDATE tasks SET done = true WHERE task_id = ?");
            markCompleteStatement.setInt(1, taskId);
            int rowsUpdated = markCompleteStatement.executeUpdate();
            
            if (rowsUpdated > 0) {
                // Task marked as complete successfully
                JOptionPane.showMessageDialog(null, "Task marked as complete.");
                // Refresh the task display
                refreshTaskDisplay();
            } else {
                // Task marking as complete failed
                JOptionPane.showMessageDialog(null, "Failed to mark task as complete.");
            }
            markCompleteStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error marking task as complete: " + ex.getMessage());
        }
    }
    

    private String getDescription(int taskId) throws SQLException {
        PreparedStatement statement = connector.prepareStatement("SELECT description FROM tasks WHERE task_id = ?");
        statement.setInt(1, taskId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        String description = resultSet.getString("description");
        resultSet.close();
        statement.close();
        return description;
    }

    private void refreshTaskDisplay() {
        // Clear the current task display
        tasksPanel.removeAll();
        tasksPanel.revalidate();
        tasksPanel.repaint();
    
        // Fetch and display the updated task list from the database
        displayPendingTasks();
    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerFrame());
    }
}
