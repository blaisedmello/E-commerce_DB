package DBhandling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String JDBC_URL = "jdbc:mysql://database-0.cluster-chzlxijptif9.us-east-2.rds.amazonaws.com:3306/OnlineShoppingDB";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "db-password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}