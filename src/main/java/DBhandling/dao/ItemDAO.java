package DBhandling.dao;

import DBhandling.*;
import DBhandling.entity.Item;
import DBhandling.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    List<Item> getAllItems() throws SQLException;

    
}

