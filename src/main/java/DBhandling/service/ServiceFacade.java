package DBhandling.service;

import DBhandling.ConnectionManager;
import DBhandling.entity.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Facade class for services
//This class is used to provide a simplified interface to the services
public class ServiceFacade {
    private final OrderService orderService;
    private final InventoryService inventoryService;

    public ServiceFacade(Connection connection) {
        this.orderService = new OrderService(connection);
        this.inventoryService = new InventoryService(connection);
    }

    public void processOrder(Order order) throws SQLException {
        orderService.processOrder(order);
    }

    public List<String> getAllInventory() throws SQLException {
        return inventoryService.getAllInventory();
    }

    public List<String> getInventoryByCategory(List<String> categories) throws SQLException {
        return inventoryService.getInventoryByCategory(categories);
    }
    public List<String> getOrderItemsByCustomerID(int customerID) throws SQLException {
        return orderService.getOrderItemsByCustomerID(customerID);
    }
    public static void main (String[] args) throws SQLException {
        System.out.println("ServiceFacade class");
        ServiceFacade serviceFacade = new ServiceFacade(ConnectionManager.getConnection());

        //get all inventory
        List<String> inventory = serviceFacade.getAllInventory();
        /*
        System.out.println("Inventory:");
        for (String itemInfo : inventory) {
            System.out.println(itemInfo);
        }

        */
        //get inventory by category
        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Computers");
        List<String> inventoryByCategory = serviceFacade.getInventoryByCategory(categories);
        System.out.println("Inventory by category:");
        for (String itemInfo : inventoryByCategory) {
            System.out.println(itemInfo);
        }
        // get items by customer ID
        List<String> itemsByCustomerID = serviceFacade.getOrderItemsByCustomerID(1);
        System.out.println("Items by customer ID:");
        for (String itemInfo : itemsByCustomerID) {
            System.out.println(itemInfo);
        }



        /*
        ArrayList<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(1, 2));
        items.add(new OrderItem(2, 1));

        Order order = new Order(9, items, "2022-01-01");
        serviceFacade.processOrder(order);
        */
    }

}