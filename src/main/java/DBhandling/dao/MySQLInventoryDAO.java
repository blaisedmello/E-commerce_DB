package DBhandling.dao;

import DBhandling.ConnectionManager;
import DBhandling.entity.Order;
import DBhandling.entity.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DBhandling.entity.Item;
//implementation of InventoryDAO: MySQL
public class MySQLInventoryDAO implements InventoryDAO {
    private Connection connection;

    public MySQLInventoryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> getAllInventory() {
        List<String> result = new ArrayList<>();
        String query = "SELECT Inventory.itemID, ItemMaster.name, Inventory.stockQuantity " +
                "FROM Inventory " +
                "INNER JOIN ItemMaster ON Inventory.itemID = ItemMaster.itemID";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Process the row and add it to the result list
                String itemInfo = "Item ID: " + rs.getInt("itemID") +
                        ", Item Name: " + rs.getString("name") +
                        ", Quantity: " + rs.getInt("stockQuantity");
                result.add(itemInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<String> getInventoryByCategory(List<String> categories) {
        List<String> result = new ArrayList<>();
        String query = "SELECT Inventory.itemID, ItemMaster.name, Inventory.stockQuantity " +
                "FROM Inventory " +
                "INNER JOIN ItemMaster ON Inventory.itemID = ItemMaster.itemID " +
                "INNER JOIN ItemCategory ON ItemMaster.itemID = ItemCategory.itemID " +
                "INNER JOIN Categories ON ItemCategory.categoryID = Categories.categoryID " +
                "WHERE Inventory.stockQuantity > 0 AND Categories.categoryName IN (";

        // Add placeholders for each category
        for (int i = 0; i < categories.size(); i++) {
            query += "?";
            if (i < categories.size() - 1) {
                query += ", ";
            }
        }
        query += ")";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the category values
            for (int i = 0; i < categories.size(); i++) {
                stmt.setString(i + 1, categories.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Process the row and add it to the result list
                String itemInfo = "Item ID: " + rs.getInt("itemID") +
                        ", Item Name: " + rs.getString("name") +
                        ", Quantity: " + rs.getInt("stockQuantity");
                result.add(itemInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    @Override
    public List<Item> getItemByCategory(String category) {
        List<Item> result = new ArrayList<>();
        String query = "SELECT ItemMaster.itemID, ItemMaster.name, ItemMaster.price, Categories.categoryName, Inventory.stockQuantity " +
                "FROM Inventory " +
                "INNER JOIN ItemMaster ON Inventory.itemID = ItemMaster.itemID " +
                "INNER JOIN ItemCategory ON ItemMaster.itemID = ItemCategory.itemID " +
                "INNER JOIN Categories ON ItemCategory.categoryID = Categories.categoryID " +
                "WHERE Inventory.stockQuantity > 0 AND Categories.categoryName = '"+category+"';";

//        // Add placeholders for each category
//        for (int i = 0; i < categories.size(); i++) {
//            query += "?";
//            if (i < categories.size() - 1) {
//                query += ", ";
//            }
//        }
//        query += ")";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the category values
//            for (int i = 0; i < categories.size(); i++) {
//                stmt.setString(i + 1, categories.get(i));
//            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("itemID");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				category = rs.getString("categoryName");
				int stock = rs.getInt("stockQuantity");
				Item item = new Item(id, name, price, category, stock);
				result.add(item);
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public void decreaseStock (Order order) throws SQLException {
        for (OrderItem item : order.getItems()) {
            decreaseStockQuantity(item.getItemID(), item.getQuantity());
        }
    }
    private void decreaseStockQuantity(int itemID, int quantity) throws SQLException {
        String query = "UPDATE Inventory SET stockQuantity = stockQuantity - ? WHERE itemID = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, quantity);
        stmt.setInt(2, itemID);
        System.out.println(stmt);
        stmt.executeUpdate();

    }
    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            connection = ConnectionManager.getConnection();

            System.out.println("Creating statement...");
            MySQLInventoryDAO inventoryDAO = new MySQLInventoryDAO(connection);
            List<String> inventory = inventoryDAO.getAllInventory();
            for (String itemInfo : inventory) {
                System.out.println(itemInfo);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {

            }
        }

        statement = null;
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            connection = ConnectionManager.getConnection();

            System.out.println("Creating statement...");
            MySQLInventoryDAO inventoryDAO = new MySQLInventoryDAO(connection);
            List<String> categories = new ArrayList<>();
            categories.add("Electronics");
            categories.add("Computers");
            List<String> inventory = inventoryDAO.getInventoryByCategory(categories);
            for (String itemInfo : inventory) {
                System.out.println(itemInfo);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {

            }
        }

    }
}

