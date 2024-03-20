package DBhandling.service;

import DBhandling.dao.*;
import DBhandling.entity.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//Service class for Order
public class OrderService {
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final InventoryDAO inventoryDAO;
    private final Connection connection;

    public OrderService(Connection connection) {
        this.connection = connection;
        this.orderDAO = new MySQLOrderDAO(connection);
        this.inventoryDAO = new MySQLInventoryDAO(connection);
        this.orderItemDAO = new MySQLOrderItemDAO(connection);
    }

    public void processOrder(Order order) throws SQLException {
        try {
            // Disable auto-commit
            connection.setAutoCommit(false);

            // Execute queries to create the order and decrease stock quantity
            Order orderWithOrderNo = orderDAO.createOrder(order);
            orderItemDAO.createOrderItem(orderWithOrderNo);
            inventoryDAO.decreaseStock(order);

            // If all queries were successful, commit the changes
            connection.commit();
        } catch (SQLException e) {
            // If there was a problem, roll back the changes
            connection.rollback();
            throw e;
        } finally {
            // Re-enable auto-commit
            connection.setAutoCommit(true);
        }
    }
    public List<String> getOrderItemsByCustomerID(int customerID) throws SQLException {
        return orderDAO.getOrderItemsByCustomerID(customerID);
    }
}