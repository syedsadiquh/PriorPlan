import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost/todo_list";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Who~ami@2000";

    // Method to connect to the database
    public static Connection connect() { 
        Connection connection = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Test the connection
        Connection connection = connect();
        // Close the connection
        close(connection);
    }
}
