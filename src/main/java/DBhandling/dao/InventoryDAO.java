package DBhandling.dao;

import DBhandling.entity.Order;

import java.sql.SQLException;
import java.util.List;

import DBhandling.entity.Item;

//DAO for Inventory
public interface InventoryDAO {
    List<String> getAllInventory() throws SQLException;
    List<String> getInventoryByCategory(List<String> categories);
    List<Item> getItemByCategory(String categories);
    void decreaseStock(Order order) throws SQLException;
}
