package be.kdg.se2.spring.basics.domain;

/**
 * Domain objects represent your business entities.
 * They are NOT Spring beans - they're created with 'new' keyword.
 */
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(Long id, String name, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
    }
}
