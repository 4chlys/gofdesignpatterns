package be.kdg.se2.spring.basics.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Another domain object - represents a customer order.
 */
public class Order {
    private Long id;
    private String customerEmail;
    private List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;

    public Order(String customerEmail) {
        this.customerEmail = customerEmail;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        calculateTotal();
    }

    private void calculateTotal() {
        totalAmount = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
