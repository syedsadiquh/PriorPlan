import java.sql.*;

public class DatabaseConnector {
    Connection con;
    Statement s;

    public DatabaseConnector() {
        try {
            String url = "jdbc:mysql://localhost:3306/todo_list";
            String username = "root";
            String password = "Whoami@2000";
            con = DriverManager.getConnection(url, username, password);
            s = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return con.prepareStatement(query);
    }
}
