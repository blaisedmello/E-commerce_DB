package DBhandling.dao;

import DBhandling.entity.Order;
import java.sql.SQLException;
import java.util.List;

//DAO for Order
public interface OrderDAO {
    Order createOrder(Order order) throws SQLException;
    List<String> getOrderItemsByCustomerID(int customerID) throws SQLException;
}
