package DBhandling.entity;

//Entity for OrderItem
public class OrderItem {

    private final int quantity;
    private final int itemID;

    public OrderItem(int itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getItemID() {
        return itemID;
    }
}