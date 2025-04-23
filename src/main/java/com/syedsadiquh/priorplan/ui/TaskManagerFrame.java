package com.syedsadiquh.priorplan.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        tasksPanel.setBackground(new Color(170, 110, 181));
        JScrollPane scrollPane = new JScrollPane(tasksPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to return to homepage
                new HomePage().setVisible(true);
                dispose(); // Close the current frame
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(170, 110, 181));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);


        displayPendingTasks();

        setVisible(true);
    }

    private void displayPendingTasks() {
        try {
            PreparedStatement statement = connector.prepareStatement("SELECT * FROM tasks WHERE done = false ORDER BY priority ASC");
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String dueDate = resultSet.getString("due_date");
                String priority = resultSet.getString("priority");
                int taskId = resultSet.getInt("task_id");
    
                JPanel taskPanel = createTaskPanel(description, dueDate, priority, taskId);
                taskPanel.setBackground(new Color(170, 110, 181));
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
        taskPanel.setLayout(new BorderLayout());
        taskPanel.setBackground(new Color(170, 110, 181));
    
        // Vertical components panel
        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align right
        verticalPanel.setBackground(new Color(170, 110, 181));
    
        JLabel descriptionLabel = new JLabel("Description: " + description);
        JLabel dueDateLabel = new JLabel("Due Date: " + dueDate);
        JLabel priorityLabel = new JLabel("Priority: " + priority);
    
        verticalPanel.add(descriptionLabel);
        verticalPanel.add(dueDateLabel);
        verticalPanel.add(priorityLabel);
    
        // Horizontal components panel
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel.setBackground(new Color(170, 110, 181));
    
        JButton updateButton = createUpdateButton(description, dueDate, priority, taskId);
        JButton deleteButton = createDeleteButton(taskId);
        JButton completeButton = createCompleteButton(taskId);
    
        horizontalPanel.add(updateButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(completeButton);
    
        taskPanel.add(verticalPanel, BorderLayout.WEST);
        taskPanel.add(horizontalPanel, BorderLayout.EAST);
    
        return taskPanel;
    }
        
    private JButton createUpdateButton(String description, String dueDate, String priority, int taskId) {
        JButton updateButton = new JButton("Update");
        updateButton.setFocusPainted(false);
        updateButton.setBackground(new Color(3, 190, 252));
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
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(Color.RED);
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
        completeButton.setFocusPainted(false);
        completeButton.setBackground(Color.GREEN);
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
