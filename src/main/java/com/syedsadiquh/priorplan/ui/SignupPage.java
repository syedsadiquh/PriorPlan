package com.syedsadiquh.priorplan.ui;

import com.syedsadiquh.priorplan.PriorPlanApplication;
import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public SignupPage() {

        setTitle("To-Do List Signup");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 438);
        setLocation(340, 130);
        setVisible(true);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(105, 135, 100, 30);
        JLabel emailLabel = new JLabel("email:");
        emailLabel.setBounds(105, 185, 100, 30);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(105, 235, 100, 30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(105, 285, 100, 30);

        nameField = new JTextField();
        nameField.setBounds(190, 135, 150, 30);
        emailField = new JTextField();
        emailField.setBounds(190, 185, 150, 30);
        usernameField = new JTextField();
        usernameField.setBounds(190, 235, 150, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 285, 150, 30);
        signupButton = new JButton("Sign Up");
        signupButton.setBackground(new Color(24, 167, 219));
        signupButton.setBounds(350, 285, 150, 30);

        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signupButton);

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("BackgroundImage/LoginIon.png"));
        Image i4 = i5.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i4);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(130, -20, 600, 400);
        add(image1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("BackgroundImage/LoginPage.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setSize(600, 400);
        add(image);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signupUser()) {
                    JOptionPane.showMessageDialog(SignupPage.this, "Sign Up Successful!");

                } else {
                    JOptionPane.showMessageDialog(SignupPage.this, "Sign Up Failed!");
                }
            }
        });
    }

    private boolean signupUser() {

        var user = User.builder()
                .name(nameField.getText().strip())
                .email(emailField.getText().strip())
                .username(usernameField.getText().strip())
                .password(new String(passwordField.getPassword()))
                .build();
        var userRepo = (UserRepository) PriorPlanApplication.getApplicationContext().getBean("userRepository");
        try {
            userRepo.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
