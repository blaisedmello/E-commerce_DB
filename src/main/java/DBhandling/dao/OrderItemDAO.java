package DBhandling.dao;

import DBhandling.entity.Order;
import java.sql.SQLException;

//DAO for OrderItem
public interface OrderItemDAO {
    public void createOrderItem(Order order) throws SQLException;
}
