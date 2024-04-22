import java.awt.Image;
import javax.swing.*;

public class DevelopersDetailsPage extends JFrame {
    public DevelopersDetailsPage() {
        setTitle("Developers Details");
        setSize(800, 600);  // Resized frame dimensions
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/hitesh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);  // Smaller resized image dimensions with SCALE_SMOOTH
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 50, 200, 150);  // Updated bounds to match resized image size
        add(image);

        ImageIcon j1 = new ImageIcon(ClassLoader.getSystemResource("Backgroundimage/Syed.jpg"));
        Image j2 = j1.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);  // Smaller resized image dimensions with SCALE_SMOOTH
        ImageIcon j3 = new ImageIcon(j2);
        JLabel image1 = new JLabel(j3);
        image1.setBounds(450, 50, 200, 150);  // Updated bounds to match resized image size
        add(image1);

        // Developer's name
        JLabel nameLabel1 = new JLabel("Developer: Mohanty Hitesh Rabindranath");
        nameLabel1.setBounds(50, 220, 300, 20);  // Updated bounds
        add(nameLabel1);

        // Developer's email ID
        JLabel emailLabel1 = new JLabel("Email: mohantyhitesh");
        emailLabel1.setBounds(50, 250, 300, 20);  // Updated bounds
        add(emailLabel1);

        // Developer's image
        ImageIcon developerImageIcon = new ImageIcon("developer_image.jpg");
        Image developerImage = developerImageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);  // Smaller resized image dimensions with SCALE_SMOOTH
        ImageIcon developerImageIconResized = new ImageIcon(developerImage);
        JLabel developerImageLabel = new JLabel(developerImageIconResized);
        developerImageLabel.setBounds(600, 220, 80, 80);  // Updated bounds to match resized image size
        add(developerImageLabel);

        // Developer's name
        JLabel nameLabel = new JLabel("Developer: Syed Sadiqu Hussain");
        nameLabel.setBounds(450, 220, 300, 20);  // Updated bounds
        add(nameLabel);

        // Developer's email ID
        JLabel emailLabel = new JLabel("Email: johndoe2@example.com");
        emailLabel.setBounds(450, 250, 300, 20);  // Updated bounds
        add(emailLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DevelopersDetailsPage developersDetailsPage = new DevelopersDetailsPage();
                developersDetailsPage.setVisible(true);
            }
        });
    }
}
