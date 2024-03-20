package DBhandling.dao;

import DBhandling.ConnectionManager;
import DBhandling.entity.Item;
import DBhandling.dao.ItemDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLItemDAO implements ItemDAO{
	private Connection connection;

	public MySQLItemDAO(Connection connection) {
		this.connection = connection;
	}
	@Override
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();
		String query = "SELECT ItemMaster.itemID, ItemMaster.name, ItemMaster.price, Categories.categoryName, Inventory.stockQuantity " +
				"FROM ItemMaster " +
				"INNER JOIN Categories ON ItemMaster.categoryID = Categories.categoryID "+
				"INNER JOIN Inventory ON Inventory.itemID = ItemMaster.itemID ";

		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("itemID");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String category = rs.getString("categoryName");
				int stock = rs.getInt("stockQuantity");
				Item item = new Item(id, name, price, category, stock);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;

	}

}
