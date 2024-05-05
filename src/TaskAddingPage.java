import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;


public class TaskAddingPage extends JFrame {
    private JTextField taskDescriptionField;
    JDateChooser dueDateField;
    private JComboBox<String> priorityComboBox;
    private JTextArea displayArea;

    public TaskAddingPage() {
        setTitle("Task Adding Page");
        setSize(600, 500);  // Increased frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Task Adding Page");
        titleLabel.setPreferredSize(new Dimension(30, 40));
        titleLabel.setBounds(220, -20, 200, 80);  // Adjusted position
        add(titleLabel);

        JLabel taskDescriptionLabel = new JLabel("Task Description:");
        taskDescriptionLabel.setBounds(50, 50, 120, 20);  // Adjusted position
        add(taskDescriptionLabel);
        taskDescriptionField = new JTextField();
        taskDescriptionField.setBounds(180, 50, 350, 20);  // Adjusted size
        add(taskDescriptionField);

        JLabel dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(50, 80, 120, 20);  // Adjusted position
        add(dueDateLabel);
        dueDateField =  new JDateChooser();
        dueDateField.setBounds(180, 80, 350, 20);  // Adjusted size
        add(dueDateField);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(50, 110, 120, 20);  // Adjusted position
        add(priorityLabel);
        String[] priorities = {"High", "Medium", "Low"};
        priorityComboBox = new JComboBox<>(priorities);
        priorityComboBox.setBounds(180, 110, 350, 20);  // Adjusted size
        add(priorityComboBox);

        JButton addButton = new JButton("Add Task");
        addButton.setBounds(220, 140, 150, 30);  // Adjusted position
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        add(addButton);

        JLabel displayLabel = new JLabel("Tasks:");
        displayLabel.setBounds(50, 180, 100, 20);  // Adjusted position
        add(displayLabel);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 200, 490, 180);  // Adjusted size
        add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(220, 400, 150, 30);  // Adjusted position
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current frame
                new HomePage().setVisible(true); // Show the HomePage frame
            }
        });
        add(backButton); // Add the button to the frame
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/Taskadding.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 600, 500);
        add(image);

    }

    private void addTask() {
        String taskDescription = taskDescriptionField.getText();
        String dueDate = ((JTextField) dueDateField.getDateEditor().getUiComponent()).getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        // Add task to database

        displayArea.append("Task: " + taskDescription + ", Due Date: " + dueDate + ", Priority: " + priority + "\n");
        clearFields();
    }

    private void clearFields() {
        taskDescriptionField.setText("");
        dueDateField.setToolTipText("");
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
