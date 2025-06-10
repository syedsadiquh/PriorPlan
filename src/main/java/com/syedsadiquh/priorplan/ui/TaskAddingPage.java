package com.syedsadiquh.priorplan.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import com.syedsadiquh.priorplan.PriorPlanApplication;
import com.syedsadiquh.priorplan.dao.TaskDAO;
import com.syedsadiquh.priorplan.globals.Global;
import com.syedsadiquh.priorplan.models.Task;
import com.syedsadiquh.priorplan.models.enums.TaskStatus;
import com.syedsadiquh.priorplan.repository.TaskRepository;
import com.toedter.calendar.JDateChooser;

import java.util.ArrayList;
import java.util.List;


public class TaskAddingPage extends JFrame {
    private JTextField taskTitleField;
    private JTextField taskDescriptionField;
    private JDateChooser dueDateField;
    private JComboBox<String> priorityComboBox;
    private JTextArea displayArea;
 
    public TaskAddingPage() {
        setTitle("Task Adding Page");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        Font boldFont = new Font(null, Font.BOLD, 14); // Change the font size as needed

        JLabel titleLabel = new JLabel("Task Adding Page");
        titleLabel.setPreferredSize(new Dimension(30, 40));
        titleLabel.setBounds(220, -20, 200, 80);
        titleLabel.setFont(boldFont);
        add(titleLabel);

        JLabel taskTitleLabel = new JLabel("Task Title:");
        taskTitleLabel.setBounds(50, 30, 150, 20);
        taskTitleLabel.setFont(boldFont);
        add(taskTitleLabel);

        taskTitleField = new JTextField();
        taskTitleField.setBounds(200, 30, 350, 20);
        add(taskTitleField);

        JLabel taskDescriptionLabel = new JLabel("Task Description:");
        taskDescriptionLabel.setBounds(50, 55, 150, 20);
        taskDescriptionLabel.setFont(boldFont);
        add(taskDescriptionLabel);

        taskDescriptionField = new JTextField();
        taskDescriptionField.setBounds(200, 55, 350, 20);
        add(taskDescriptionField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(50, 80, 120, 20);
        dueDateLabel.setFont(boldFont);
        add(dueDateLabel);
        
        dueDateField = new JDateChooser();
        dueDateField.setBounds(200, 80, 350, 20);
        add(dueDateField);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(50, 110, 120, 20);
        priorityLabel.setFont(boldFont);
        add(priorityLabel);
        String[] priorities = {"HIGH", "MEDIUM", "LOW"};
        priorityComboBox = new JComboBox<>(priorities);
        priorityComboBox.setBounds(200, 110, 350, 20);
        add(priorityComboBox);

        JButton addButton = new JButton("Add Task");
        addButton.setBounds(220, 140, 150, 30);
        addButton.setFont(boldFont);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        add(addButton);

        JLabel displayLabel = new JLabel("Tasks:");
        displayLabel.setBounds(50, 180, 100, 20);
        displayLabel.setFont(boldFont);
        add(displayLabel);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 200, 490, 180);
        add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(220, 400, 150, 30);
        backButton.setFont(boldFont);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new HomePage().setVisible(true);
            }
        });
        add(backButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("BackgroundImage/TaskAdding.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 600, 500);
        add(image);

        // Fetch and display tasks from the database
        displayTasksFromDatabase();
    }

    private void addTask() {
        String taskTitle = taskTitleField.getText();
        String taskDescription = taskDescriptionField.getText();
        String dueDate = ((JTextField) dueDateField.getDateEditor().getUiComponent()).getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        
        // insert task details into the database
        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        var res = new TaskDAO(taskRepo).insertTask(Global.user, taskTitle, taskDescription, priority, dueDate);
        if (res) {
            // Task inserted successfully
            System.out.println("New Task Added");
            displayTasksFromDatabase();
        } else {
            // Failed to insert a task
            System.out.println("Failed to insert task.");
        }
    
        clearFields();
    }
    private void displayTasksFromDatabase() {

        var taskRepo = (TaskRepository) PriorPlanApplication.getApplicationContext().getBean("taskRepository");
        List<Task> notStartedTasks = taskRepo.findTaskByUserAndStatus(Global.user, TaskStatus.NOT_STARTED);
        List<Task> inProgressTasks = taskRepo.findTaskByUserAndStatus(Global.user, TaskStatus.IN_PROGRESS);
        List<Task> tasks = new ArrayList<Task>();
        tasks.addAll(notStartedTasks);
        tasks.addAll(inProgressTasks);
        if (tasks.isEmpty()) {
            displayArea.append("No Task Added yet...");
        }
        else {
            displayArea.setText("");
            for (Task t : tasks) {
                String taskDescription = t.getTitle();
                String dueDate = t.getDueDate().toLocalDate().toString();
                String priority = t.getPriority().toString();
                displayArea.append("Task: " + taskDescription + ", Due Date: " + dueDate + ", Priority: " + priority + "\n");
            }
        }


    }
    
 
    private void clearFields() {
        taskDescriptionField.setText("");
        dueDateField.setDate(null);
        priorityComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TaskAddingPage taskAddingPage = new TaskAddingPage();
                taskAddingPage.setVisible(true);
            }
        });
    }
}
 