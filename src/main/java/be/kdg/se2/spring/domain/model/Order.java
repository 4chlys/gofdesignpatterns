package be.kdg.se2.spring.domain.model;

import be.kdg.se2.spring.domain.valueobjects.Money;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private UUID id;
    private String customerEmail;
    private List<OrderLine> lines;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor enforces invariants
    public Order(String customerEmail) {
        if (customerEmail == null || customerEmail.isBlank()) {
            throw new IllegalArgumentException("Customer email is required");
        }
        this.id = UUID.randomUUID();
        this.customerEmail = customerEmail;
        this.lines = new ArrayList<>();
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    // Business methods with domain logic
    public void addLine(OrderLine line) {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot modify order in status: " + status);
        }
        this.lines.add(line);
        this.updatedAt = LocalDateTime.now();
    }

    public void confirm() {
        if (lines.isEmpty()) {
            throw new IllegalStateException("Cannot confirm empty order");
        }
        this.status = OrderStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel delivered order");
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    // Calculated business values
    public Money getTotalAmount() {
        return lines.stream()
                .map(OrderLine::getLineTotal)
                .reduce(Money.ZERO, Money::add);
    }

    public boolean canBeModified() {
        return status == OrderStatus.CREATED;
    }

    // Getters only (immutability where possible)
    public UUID getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public List<OrderLine> getLines() { return new ArrayList<>(lines); }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

// ----------------------------------------------------------------------------
