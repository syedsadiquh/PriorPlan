import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginSignupGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public LoginSignupGUI() {

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

        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/LoginIon.png"));
        Image i4 = i5.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i4);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(130, -20, 600, 400);
        add(image1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/LoginPage.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setSize(600,400);
        add(image);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate user credentials (mock validation)
                // TODO: set multi-user support. 
                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(LoginSignupGUI.this, "Login Successful!");
                    openHomePage(username);
                } else {
                    JOptionPane.showMessageDialog(LoginSignupGUI.this, "Invalid username or password.");
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (signupUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginSignupGUI.this, "Sign Up Successful!");
                } else {
                    JOptionPane.showMessageDialog(LoginSignupGUI.this, "Sign Up Failed!");
                }
            }
        });
    }

    private boolean signupUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void openHomePage(String username) {
        // Open the home page with the given username
        new HomePage().setVisible(true);
        setVisible(false); // Hide the login/signup page
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginSignupGUI().setVisible(true);
            }
        });
    }
}

class HomePages extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;
    private LoginSignupGUI parentFrame;

    public HomePages(String username, LoginSignupGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("To-Do List Home");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        welcomeLabel = new JLabel("Welcome, " + username + "!");
        panel.add(welcomeLabel);

        logoutButton = new JButton("Logout");
        panel.add(logoutButton);

        add(panel);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the home page
                parentFrame.setVisible(true); // Show the login/signup page
            }
        });
    }
}
