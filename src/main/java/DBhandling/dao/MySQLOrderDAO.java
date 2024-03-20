package DBhandling.dao;

import DBhandling.ConnectionManager;
import DBhandling.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// implementation of OrderDAO: MySQL

public class MySQLOrderDAO implements OrderDAO {
    private Connection connection;

    public MySQLOrderDAO(Connection connection) {
        this.connection = connection;
    }

    public Order createOrder(Order order) throws SQLException {


            // Execute queries to create the order and add order items
            String createOrderQuery = "INSERT INTO Orders (date, customerID, orderStatus) VALUES (?, ?, 'Processing')";
            try (PreparedStatement stmt = connection.prepareStatement(createOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, order.getOrderDate());
                stmt.setInt(2, order.getCustomerID());
                System.out.println(stmt);
                stmt.executeUpdate();

                // Retrieve the auto-generated key
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1));
                    }
                }
            }
            return order;
    }
    public List<String> getOrderItemsByCustomerID(int customerID) throws SQLException {
        List<String> orderItems = new ArrayList<>();
        String getOrderItemsQuery = "SELECT * FROM OrderItems " +
                "INNER JOIN Orders ON OrderItems.orderID = Orders.orderID " +
                "INNER JOIN ItemMaster ON OrderItems.itemID = ItemMaster.itemID " +
                "WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(getOrderItemsQuery)) {
            stmt.setInt(1, customerID);
            System.out.println(stmt);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String orderItem = String.format("Order ID: %d, ItemName: %s, Price: %d, Quantity: %d",
                            rs.getInt("orderID"), rs.getString("name"), rs.getInt("price"), rs.getInt("quantity"));
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }

    public static void main(String[] args) throws SQLException {
        // some code goes here
        ArrayList<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(1, 20));
        items.add(new OrderItem(2, 10));

        Order order = new Order(1, items, "2021-01-01");
        MySQLOrderDAO orderDAO = new MySQLOrderDAO(ConnectionManager.getConnection());
        orderDAO.createOrder(order);

    }
}

