package DBhandling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import DBhandling.entity.Order;
import DBhandling.entity.OrderItem;

// Implementation of OrderItemDAO: MySQL

public class MySQLOrderItemDAO implements OrderItemDAO{
    private final Connection connection;
    public MySQLOrderItemDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createOrderItem(Order order) throws SQLException {
        for (OrderItem item : order.getItems()) {
            String addItemQuery = "INSERT INTO OrderItems (orderID, itemID, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(addItemQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, item.getItemID());
            stmt.setInt(3, item.getQuantity());
            stmt.executeUpdate();
        }
    }
}


