package be.kdg.se2.spring.domain.model;

import be.kdg.se2.spring.domain.valueobjects.Money;
import java.util.UUID;

public class OrderLine {

    private final UUID productId;
    private final int quantity;
    private final Money unitPrice;

    public OrderLine(UUID productId, int quantity, Money unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Money getLineTotal() {
        return unitPrice.multiply(quantity);
    }

    public UUID getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public Money getUnitPrice() { return unitPrice; }
}
