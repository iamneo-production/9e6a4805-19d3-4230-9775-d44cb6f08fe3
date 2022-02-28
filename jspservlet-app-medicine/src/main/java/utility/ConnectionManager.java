package utility;
import java.sql.*;

public class ConnectionManager {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname","root", "examly");
         }
         catch(SQLException e) {
            e.printStackTrace();
         }
         catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
        return connection;
    }
}