import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;


public class TaskAddingPage extends JFrame {
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

        JLabel taskDescriptionLabel = new JLabel("Task Description:");
        taskDescriptionLabel.setBounds(50, 50, 150, 20);
        taskDescriptionLabel.setFont(boldFont);
        add(taskDescriptionLabel);

        taskDescriptionField = new JTextField();
        taskDescriptionField.setBounds(200, 50, 350, 20);
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
        String[] priorities = {"High", "Medium", "Low"};
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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/Taskadding.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 600, 500);
        add(image);

        // Fetch and display tasks from the database
        displayTasksFromDatabase();
    }

    private void addTask() {
        String taskDescription = taskDescriptionField.getText();
        String dueDate = ((JTextField) dueDateField.getDateEditor().getUiComponent()).getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        
        // Use DatabaseConnector to insert task details into the database
        try {
            DatabaseConnector connector = new DatabaseConnector();
            PreparedStatement statement = connector.prepareStatement("INSERT INTO tasks (description, due_date, priority) VALUES (?, ?, ?)");
            statement.setString(1, taskDescription);
            statement.setString(2, dueDate);
            statement.setString(3, priority);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Task inserted successfully
                displayArea.append("Task: " + taskDescription + ", Due Date: " + dueDate + ", Priority: " + priority + "\n");
            } else {
                // Failed to insert task
                System.out.println("Failed to insert task.");
            }
            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or SQL error
        }
    
        clearFields();
    }
    private void displayTasksFromDatabase() {
        try {
            DatabaseConnector connector = new DatabaseConnector();
            Statement statement = connector.con.createStatement(); // Using con from DatabaseConnector
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
            while (resultSet.next()) {
                String taskDescription = resultSet.getString("description");
                String dueDate = resultSet.getString("due_date");
                String priority = resultSet.getString("priority");
                displayArea.append("Task: " + taskDescription + ", Due Date: " + dueDate + ", Priority: " + priority + "\n");
            }
            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection or SQL error
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
 