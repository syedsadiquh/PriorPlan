package com.syedsadiquh.priorplan.ui;

import com.syedsadiquh.priorplan.PriorPlanApplication;
import com.syedsadiquh.priorplan.dao.UserDAO;
import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginPage() {

        setTitle("To-Do List Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(600, 438);
        setLocation(340, 130);
        setVisible(true);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(105, 135, 100, 30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(105, 185, 100, 30);
        
        usernameField = new JTextField();
        usernameField.setBounds(190, 135, 150, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 185, 150, 30);
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 280, 150, 30);
        loginButton.setBackground(new Color(24, 167, 219));
        signupButton = new JButton("Sign Up");
        signupButton.setBackground(new Color(24, 167, 219));
        signupButton.setBounds(350, 280, 150, 30);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
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
        image.setSize(600,400);
        add(image);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText().strip();
                    String password = new String(passwordField.getPassword());
                    var userRepo = (UserRepository) PriorPlanApplication.getApplicationContext().getBean("userRepository");
                    boolean res = new UserDAO(userRepo).checkUserInDB(username, password);
                    if (res) {
                        setVisible(false);
                        new HomePage().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignupPage().setVisible(true);
            }
        });
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}

//class HomePages extends JFrame {
//    private JLabel welcomeLabel;
//    private JButton logoutButton;
//    // private LoginSignupGUI parentFrame;
//
//    public HomePages(String username, LoginSignupGUI parentFrame) {
//        // this.parentFrame = parentFrame;
//
//        setTitle("To-Do List Home");
//        setSize(300, 200);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new FlowLayout());
//        welcomeLabel = new JLabel("Welcome, " + username + "!");
//        panel.add(welcomeLabel);
//
//        logoutButton = new JButton("Logout");
//        panel.add(logoutButton);
//
//        add(panel);
//
//    }
//}
 