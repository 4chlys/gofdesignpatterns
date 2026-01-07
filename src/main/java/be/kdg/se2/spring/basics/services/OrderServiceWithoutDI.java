package be.kdg.se2.spring.basics.services;

import be.kdg.se2.spring.basics.domain.Order;
import be.kdg.se2.spring.basics.persistence.OrderRepository;
import be.kdg.se2.spring.basics.persistence.ProductRepository;

import java.util.Map;

/**
 * BAD EXAMPLE - Tightly coupled version (DON'T DO THIS)
 * <p>
 * Problems:
 * ✗ Hard to test - can't replace dependencies with mocks
 * ✗ Tight coupling - OrderService directly creates its dependencies
 * ✗ Hidden dependencies - not clear what this public class needs
 * ✗ Cannot swap implementations
 * ✗ Difficult to manage lifecycle
 */
public class OrderServiceWithoutDI {

    // Creating dependencies directly - TIGHT COUPLING
    private final OrderRepository orderRepository = new OrderRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final NotificationService notificationService = new NotificationService();

    // Same business logic, but impossible to test properly
    public Order createOrder(String customerEmail, Map<Long, Integer> productQuantities) {
        // ... implementation identical to OrderService ...
        return null; // placeholder
    }

    // How do you test this? You can't mock the dependencies!
    // How do you use a different notification service? You can't!
    // How do you use a real database? You have to modify this public class!
}
