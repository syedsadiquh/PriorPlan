import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutPage extends JFrame {
    public AboutPage() {
        setTitle("About Prior Plan");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setFont(new Font("Calibri", Font.PLAIN, 16));
        aboutTextArea.setBackground(Color.CYAN);

        aboutTextArea.setText(
                " About:\n" +
                " This ToDo List App is designed to help you manage and organize your daily tasks efficiently.\n\n" +
                "   How It Works:\n" +
                "       1. Add New Tasks: Simply type the task name and click the 'Add Task' button to add it to the list.\n" +
                "       2. Prioritize Tasks: You can set the priority of each task by marking it as High, Medium, or Low.\n" +
                "       3. Manage Tasks: Easily edit, delete, or mark tasks as completed as needed.\n\n" +
                " Features:\n" +
                "    - Add, Edit, and Delete Tasks\n" +
                "    - Set Task Priority (High, Medium, Low)\n" +
                "    - Mark Tasks as Completed\n" +
                "    - Search and Filter Tasks\n" +
                "    - User-Friendly Interface\n\n" +
                " Thank you for using our ToDo List App!");

        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        scrollPane.setPreferredSize(new Dimension(580, 300));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current frame
                new HomePage().setVisible(true); // Show the HomePage frame
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AboutPage();
            }
        });
    }
}
