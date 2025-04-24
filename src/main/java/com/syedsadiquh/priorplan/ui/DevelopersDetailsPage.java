package com.syedsadiquh.priorplan.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.swing.*;

public class DevelopersDetailsPage extends JFrame {
    public DevelopersDetailsPage() {
        setTitle("Developers Details");
        setSize(600, 400); // Updated frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set background color of the content pane
        getContentPane().setBackground(new Color(3, 190, 252));

        // Load and resize images
        ImageIcon hiteshIcon = new ImageIcon(getClass().getResource("/BackgroundImage/hitesh.jpg"));
        Image hiteshImage = hiteshIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon hiteshScaledIcon = new ImageIcon(hiteshImage);

        ImageIcon syedIcon = new ImageIcon(getClass().getResource("/BackgroundImage/Syed.jpg"));
        Image syedImage = syedIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon syedScaledIcon = new ImageIcon(syedImage);

        // Developer 1
        JLabel hiteshImageLabel = new JLabel(hiteshScaledIcon);
        hiteshImageLabel.setBounds(50, 50, 200, 150);
        add(hiteshImageLabel);

        JLabel nameLabel1 = new JLabel("Developer: Mohanty Hitesh Rabindranath");
        nameLabel1.setBounds(50, 220, 300, 20);
        nameLabel1.setFont(new Font("Calibri", Font.BOLD, 14));
        add(nameLabel1);

        JLabel emailLabel1 = new JLabel("Email: mohantyhitesh4495@gmail.com");
        emailLabel1.setBounds(50, 250, 300, 20);
        emailLabel1.setFont(new Font("Calibri", Font.BOLD, 14));
        add(emailLabel1);

        // Developer 2
        JLabel syedImageLabel = new JLabel(syedScaledIcon);
        syedImageLabel.setBounds(330, 50, 200, 150);
        add(syedImageLabel);

        JLabel nameLabel2 = new JLabel("Developer: Syed Sadiqu Hussain");
        nameLabel2.setBounds(330, 220, 300, 20);
        nameLabel2.setFont(new Font("Calibri", Font.BOLD, 14));
        add(nameLabel2);

        JLabel emailLabel2 = new JLabel("Email: syedsadiquh@gmail.com");
        emailLabel2.setBounds(330, 250, 300, 20);
        emailLabel2.setFont(new Font("Calibri", Font.BOLD, 14));
        add(emailLabel2);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(250, 300, 100, 30);
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.RED); // Setting background color to transparent
        backButton.addActionListener(e -> {
            setVisible(false);
            new HomePage().setVisible(true);
        });
        add(backButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DevelopersDetailsPage().setVisible(true);
            }
        });
    }
}
