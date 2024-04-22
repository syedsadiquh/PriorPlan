import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener {
    JButton Add, developer, about;

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
        Add.setBounds(100, 150, 150, 40);
        Add.addActionListener(this); // Add ActionListener to the button
        Add.setFocusPainted(false);
        Add.setBackground(new Color(3, 190, 252));
        Add.setForeground(Color.BLACK);
        add(Add);

        developer = new JButton("Developer Details");
        developer.setBounds(100, 250, 150, 40);
        developer.addActionListener(this); // Add ActionListener to the button
        developer.setFocusPainted(false);
        developer.setBackground(new Color(3, 190, 252));
        developer.setForeground(Color.BLACK);
        add(developer);

        about = new JButton("About");
        about.setBounds(100, 200, 150, 40);
        about.addActionListener(this); // Add ActionListener to the button
        about.setFocusPainted(false);
        about.setBackground(new Color(3, 190, 252));
        about.setForeground(Color.BLACK);
        add(about);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/Homepage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 400);
        add(image);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Add) {
            setVisible(false);
            new TaskAddingPage().setVisible(true);
        } else if (ae.getSource() == developer) {
            setVisible(false);
            new DevelopersDetailsPage().setVisible(true);
        } else if (ae.getSource() == about) {
            setVisible(false);
            new AboutPage().setVisible(true);
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
