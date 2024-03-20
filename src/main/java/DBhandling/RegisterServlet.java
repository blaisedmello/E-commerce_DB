package DBhandling;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String JDBC_URL = "jdbc:mysql://database-0.cluster-chzlxijptif9.us-east-2.rds.amazonaws.com:3306/OnlineShoppingDB";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "db-password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			String sqlQuery = "INSERT INTO Customers (firstName, lastName, address, email, password) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, password);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				response.sendRedirect("registration-success.jsp");
			} else {
				response.sendRedirect("registration-error.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Registration failed: " + e.getMessage());
		    request.getRequestDispatcher("error.jsp").forward(request, response);
			//response.sendRedirect("registration-error.jsp");
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
