package DBhandling.entity;

public class Item {
    public Item(int id, String name, double price, String category, int stock) {
		this.id = id;
    	this.category = category;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
    private int id;
	private String name;
    private double price;
    private String category;
    private int stock;
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    
    public int getStock() {
        return stock;
    }


    // Constructor, getters, and setters
}