package DBhandling.entity;

import java.util.List;

//Entity for Order

public class Order {
    private int orderID;
    private final int customerId;
    private final List<OrderItem> items;
    private final String orderDate;

    public Order(int customerId, List<OrderItem> items, String orderDate) {

        this.customerId = customerId;
        this.items = items;
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public String getOrderDate() {
        return orderDate;
    }

    public int getCustomerID() {
        return customerId;
    }

    public void setOrderId(int orderID) {
        this.orderID = orderID;
    }
    public int getOrderID() {
        return orderID;
    }
}