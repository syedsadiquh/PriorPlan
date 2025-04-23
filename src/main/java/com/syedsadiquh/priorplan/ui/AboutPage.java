package com.syedsadiquh.priorplan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutPage extends JFrame {
    public AboutPage() {
        setTitle("About Prior Plan");
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed to dispose on close
        setLocationRelativeTo(null);

        // Set frame background color to cyan
        getContentPane().setBackground(new Color(3, 190, 252));

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setEditable(false);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setFont(new Font("Calibri", Font.BOLD, 16));
        aboutTextArea.setBackground(new Color(3, 190, 252));

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
                        " Thank you for using our ToDo List App!\n"+
                        " Feel free to Contact Us :)");

        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        scrollPane.setPreferredSize(new Dimension(580, 300));

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setFocusPainted(false);
        // Adding ActionListener to back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the current frame
                new HomePage().setVisible(true); // Show the HomePage frame
            }
        });

        // Setting layout to null for direct component placement
        setLayout(null);

        // Positioning components
        scrollPane.setBounds(0, 0, 700, 350);
        backButton.setBounds(300, 350, 100, 30);

        // Adding components to the frame
        add(scrollPane);
        add(backButton);

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
