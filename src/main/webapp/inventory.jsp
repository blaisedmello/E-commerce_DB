<%@ page import="java.util.List" %>
<%@ page import="DBhandling.dao.MySQLInventoryDAO" %>
<%@ page import="DBhandling.ConnectionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory</title>
</head>
<body>
    <h1>Inventory</h1>
    <ul>
        <%-- Use Java code to retrieve items from the DAO --%>
        <% 
            // Instantiate the DAO class
            MySQLInventoryDAO inventoryDAO = new MySQLInventoryDAO(ConnectionManager.getConnection());
            
            // Retrieve the inventory items
            List<String> inventory = inventoryDAO.getAllInventory();
            
            // Iterate over the list of items and display them
            for (String itemInfo : inventory) {
        %>
            <li><%= itemInfo %></li>
        <% 
            } // End of loop
        %>
    </ul>
</body>
</html>
