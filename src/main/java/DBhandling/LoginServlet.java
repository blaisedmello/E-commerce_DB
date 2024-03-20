package DBhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://database-0.cluster-chzlxijptif9.us-east-2.rds.amazonaws.com:3306/OnlineShoppingDB";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "db-password";
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            int customerId = authenticateUser(email, password); // Retrieve customer ID
            if (customerId != -1) {
                // Store customer ID in session
                HttpSession session = request.getSession();
                session.setAttribute("customerId", customerId);

                // Redirect to login-success.jsp
                response.sendRedirect("login-success.jsp");
            } else {
                // Redirect to error.jsp
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Redirect to error.jsp with error message
            response.sendRedirect("error.jsp?errorMessage=" + e.getMessage());
        }
    }

    // Method to authenticate user and retrieve customer ID
    private int authenticateUser(String email, String password) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int customerId = -1; // Default value if authentication fails

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String sql = "SELECT customerId FROM Customers WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                customerId = rs.getInt("customerId"); // Retrieve customer ID
            }
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return customerId;
    }
}
