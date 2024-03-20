package DBhandling;
import java.sql.*;

public class TestConnect {
	private static final String JDBC_URL = "jdbc:mysql://database-0.cluster-chzlxijptif9.us-east-2.rds.amazonaws.com:3306/OnlineShoppingDB";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "db-password";

	public static void main(String[] args) {
		Statement statement = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			System.out.println("Creating statement...");
			statement = connection.createStatement();
			String sqlQuery = "SELECT * FROM Customers";
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				String columnValue = resultSet.getString("firstName");
				System.out.println("Column value: " + columnValue);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) statement.close();
			} catch (SQLException se2) {
			} 
			try {
				if (connection != null) connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} 
		} 

	}
}
