package com.syedsadiquh.priorplan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {
    JButton Add, developer, about, manage;

    public HomePage() {
        setTitle("Home Page");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel appname = new JLabel("Prior Plan:");
        appname.setBounds(105, 35, 500, 30);
        appname.setForeground(Color.BLACK);
        appname.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        add(appname);

        JLabel desc = new JLabel("The Ultimate To-Do list");
        desc.setBounds(20, 70, 500, 30);
        desc.setForeground(Color.BLACK);
        desc.setFont(new Font("SAN_SERIF", Font.BOLD, 30));
        add(desc);

        Add = new JButton("Add Task");
        Add.setBounds(100, 130, 150, 40);
        Add.addActionListener(this); // Add ActionListener to the button
        Add.setFocusPainted(false);
        Add.setBackground(new Color(3, 190, 252));
        Add.setForeground(Color.BLACK);
        add(Add);

        developer = new JButton("Developer Details");
        developer.setBounds(100, 230, 150, 40);
        developer.addActionListener(this); // Add ActionListener to the button
        developer.setFocusPainted(false);
        developer.setBackground(new Color(3, 190, 252));
        developer.setForeground(Color.BLACK);
        add(developer);

        about = new JButton("About");
        about.setBounds(100, 280, 150, 40);
        about.addActionListener(this); // Add ActionListener to the button
        about.setFocusPainted(false);
        about.setBackground(new Color(3, 190, 252));
        about.setForeground(Color.BLACK);
        add(about);

        manage = new JButton("Manage-Task");
        manage.setBounds(100, 180, 150, 40);
        manage.addActionListener(this); // Add ActionListener to the button
        manage.setFocusPainted(false);
        manage.setBackground(new Color(3, 190, 252));
        manage.setForeground(Color.BLACK);
        add(manage);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Homepage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 400);
        add(image);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        if (ae.getSource() == Add) {
            new TaskAddingPage().setVisible(true);
        } else if (ae.getSource() == developer) {
            new DevelopersDetailsPage().setVisible(true);
        } else if (ae.getSource() == about) {
            new AboutPage().setVisible(true);
        } else if (ae.getSource() == manage){
            new TaskManagerFrame().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
            }
        });
    }
}
