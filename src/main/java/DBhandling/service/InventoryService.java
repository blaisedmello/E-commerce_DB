package DBhandling.service;

import DBhandling.dao.InventoryDAO;
import DBhandling.dao.MySQLInventoryDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//Service class for Inventory
public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService(Connection connection) {
        this.inventoryDAO = new MySQLInventoryDAO(connection);
    }

    public List<String> getAllInventory() throws SQLException {
        return inventoryDAO.getAllInventory();
    }

    public List<String> getInventoryByCategory(List<String> categories) throws SQLException {
        return inventoryDAO.getInventoryByCategory(categories);
    }
}