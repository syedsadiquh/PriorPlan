package com.syedsadiquh.priorplan.ui;

import com.syedsadiquh.priorplan.PriorPlanApplication;
import com.syedsadiquh.priorplan.globals.Global;
import com.syedsadiquh.priorplan.models.Task;
import com.syedsadiquh.priorplan.models.enums.TaskPriority;
import com.syedsadiquh.priorplan.models.enums.TaskStatus;
import com.syedsadiquh.priorplan.repository.TaskRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TaskManagerFrame extends JFrame {
    private JPanel tasksPanel;
    public TaskManagerFrame() {
        setTitle("Task Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        tasksPanel.setBackground(new Color(170, 110, 181));
        JScrollPane scrollPane = new JScrollPane(tasksPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Logic to return to homepage
            new HomePage().setVisible(true);
            dispose(); // Close the current frame
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(170, 110, 181));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);


        displayPendingTasks();

        setVisible(true);
    }

    private void displayPendingTasks() {
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        List<Task> notStartedTasks = taskRepo.findTaskByUserAndStatus(Global.user, TaskStatus.NOT_STARTED);
        List<Task> inProgressTasks = taskRepo.findTaskByUserAndStatus(Global.user, TaskStatus.IN_PROGRESS);
        notStartedTasks.sort(Comparator.comparing(Task::getPriority));
        inProgressTasks.sort(Comparator.comparing(Task::getPriority));

        if (inProgressTasks.isEmpty() && notStartedTasks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no tasks in your task Manager");
        } else {
            for (Task t : inProgressTasks) {
                String taskTitle = t.getTitle();
                String dueDate = t.getDueDate().toLocalDate().toString();
                TaskPriority priority = (TaskPriority) t.getPriority();
                long taskId = t.getTaskId();

                JPanel taskPanel = createTaskPanel(taskTitle, dueDate, priority, taskId);
                taskPanel.setBackground(new Color(170, 110, 181));
                tasksPanel.add(taskPanel);
            }
            for (Task t : notStartedTasks) {
                String taskTitle = t.getTitle();
                String dueDate = t.getDueDate().toLocalDate().toString();
                TaskPriority priority = (TaskPriority) t.getPriority();
                long taskId = t.getTaskId();

                JPanel taskPanel = createTaskPanel(taskTitle, dueDate, priority, taskId);
                taskPanel.setBackground(new Color(170, 110, 181));
                tasksPanel.add(taskPanel);
            }
        }
    }
        
    private JPanel createTaskPanel(String title, String dueDate, TaskPriority priority, long taskId) {
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BorderLayout());
        taskPanel.setBackground(new Color(170, 110, 181));
    
        // Vertical components panel
        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setAlignmentX(Component.RIGHT_ALIGNMENT); // Align right
        verticalPanel.setBackground(new Color(170, 110, 181));
    
        JLabel titleLabel = new JLabel("Title: " + title);
        JLabel dueDateLabel = new JLabel("Due Date: " + dueDate);
        JLabel priorityLabel = new JLabel("Priority: " + priority);
    
        verticalPanel.add(titleLabel);
        verticalPanel.add(dueDateLabel);
        verticalPanel.add(priorityLabel);
    
        // Horizontal components panel
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        horizontalPanel.setBackground(new Color(170, 110, 181));
    
        JButton updateButton = createUpdateButton(title, dueDate, priority, taskId);
        JButton deleteButton = createDeleteButton(taskId);
        JButton completeButton = createCompleteButton(taskId);
    
        horizontalPanel.add(updateButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(completeButton);
    
        taskPanel.add(verticalPanel, BorderLayout.WEST);
        taskPanel.add(horizontalPanel, BorderLayout.EAST);
    
        return taskPanel;
    }
        
    private JButton createUpdateButton(String title, String dueDate, TaskPriority priority, long taskId) {
        JButton updateButton = new JButton("Update");
        updateButton.setFocusPainted(false);
        updateButton.setBackground(new Color(3, 190, 252));
        updateButton.addActionListener(e -> {
            // Create a dialog box for updating task details
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
   
            JTextField titleField = new JTextField(title);
            panel.add(new JLabel("Title:"));
            panel.add(titleField);
   
            JTextField dueDateField = new JTextField(dueDate);
            panel.add(new JLabel("Due Date:"));
            panel.add(dueDateField);
   
            JComboBox<String> priorityComboBox = new JComboBox<>();
            // Get enum values and add them to the JComboBox
            for (TaskPriority p : TaskPriority.values()) {
                priorityComboBox.addItem(String.valueOf(p));
            }
            priorityComboBox.setSelectedItem(priority);
            panel.add(new JLabel("Priority:"));
            panel.add(priorityComboBox);
   
            int result = JOptionPane.showConfirmDialog(null, panel, "Update Task",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
   
            if (result == JOptionPane.OK_OPTION) {
                String newTitle = titleField.getText();
                String newDueDate = dueDateField.getText();
                TaskPriority newPriority = TaskPriority.valueOf((String) priorityComboBox.getSelectedItem());
   
                // Update the task in the database
                updateTask(taskId, newTitle, newDueDate, newPriority);
            }
        });
        return updateButton;
    }
    
    private JButton createDeleteButton(long taskId) {
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(Color.RED);
        deleteButton.addActionListener(e -> {
            String title = getTitle(taskId);

            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the task: " + title, "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                deleteTask(taskId);
            }
        });
        return deleteButton;
    }
    
    private JButton createCompleteButton(long taskId) {
        JButton completeButton = new JButton("Complete");
        completeButton.setFocusPainted(false);
        completeButton.setBackground(Color.GREEN);
        completeButton.addActionListener(e -> {
            String title = getTitle(taskId);
            // Mark task as complete
            markTaskAsComplete(taskId);
        });
        return completeButton;
    }

    private void updateTask(long taskId, String newTitle, String newDueDate, TaskPriority newPriority) {
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        LocalDate date = LocalDate.parse(newDueDate.replace(" ", "-"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime newDateTime = date.atStartOfDay();
        var res = taskRepo.updateTaskDetails(Global.user, taskId, newTitle, newDateTime, newPriority);

        if (res == 1) {
            JOptionPane.showMessageDialog(null, "Task updated successfully.");
            refreshTaskDisplay();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update task.");
        }
    }
    
    
    private void deleteTask(long taskId) {
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        var res = taskRepo.deleteTaskByUserAndTaskId(Global.user, taskId);
        if (res == 1) {
            // Task deleted successfully
            JOptionPane.showMessageDialog(null, "Task deleted successfully.");
            // Refresh the task display
            refreshTaskDisplay();
        } else {
            // Task deletion failed
            JOptionPane.showMessageDialog(null, "Failed to delete task.");
        }
    }

    private void markTaskAsComplete(long taskId) {
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        var res = taskRepo.updateTaskStatus(Global.user, taskId, TaskStatus.COMPLETED);
        if (res == 1) {
            JOptionPane.showMessageDialog(null, "Task marked as complete.");
            refreshTaskDisplay();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to mark task as complete.");
        }
    }
    

    private String getTitle(long taskId) {
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        var task = taskRepo.getTaskByTaskId(taskId);
        return task.getTitle();
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
