package be.kdg.se2.spring.basics.services;

import be.kdg.se2.spring.basics.domain.Order;
import be.kdg.se2.spring.basics.domain.OrderStatus;
import be.kdg.se2.spring.basics.domain.Product;
import be.kdg.se2.spring.basics.persistence.OrderRepository;
import be.kdg.se2.spring.basics.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * OrderService demonstrates CONSTRUCTOR INJECTION - the recommended approach.
 * <p>
 * Why Constructor Injection?
 * ✓ Dependencies are explicit and required
 * ✓ Object is fully initialized when created
 * ✓ Easy to test - just pass mock dependencies to constructor
 * ✓ Immutable dependencies (final fields)
 * ✓ No reflection magic needed in tests
 *
 * @Service is a specialized @Component that indicates business logic.
 */
@Service
public class OrderService {

    // Dependencies are declared as final - they cannot change after construction
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final NotificationService notificationService;

    /**
     * Constructor injection - Spring automatically provides these dependencies.
     * <p>
     * How Spring does this:
     * 1. Spring finds all @Component, @Service, @Repository public classes
     * 2. When creating OrderService, it sees it needs 3 dependencies
     * 3. Spring looks in its "container" for beans of those types
     * 4. Spring calls this constructor with the found beans
     * 5. The OrderService instance is now ready to use
     * <p>
     * No @Autowired needed on constructor (since Spring 4.3)
     */
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.notificationService = notificationService;
    }

    /**
     * Business method that uses the injected dependencies.
     */
    public Order createOrder(String customerEmail, Map<Long, Integer> productQuantities) {
        // Create new order
        Order order = new Order(customerEmail);

        // Add products to order
        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            // Use injected productRepository
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

            // Check stock
            if (product.getStockQuantity() < quantity) {
                throw new IllegalStateException(
                        "Insufficient stock for " + product.getName() +
                                ". Available: " + product.getStockQuantity()
                );
            }

            // Add to order
            order.addItem(product, quantity);

            // Reduce stock
            product.setStockQuantity(product.getStockQuantity() - quantity);
            productRepository.save(product);
        }

        // Save order using injected repository
        Order savedOrder = orderRepository.save(order);

        // Send notification using injected service
        notificationService.sendNotification(
                customerEmail,
                "Order #" + savedOrder.getId() + " created successfully. Total: $" +
                        String.format("%.2f", savedOrder.getTotalAmount())
        );

        return savedOrder;
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public List<Order> getCustomerOrders(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrder(orderId);
        OrderStatus oldStatus = order.getStatus();
        order.setStatus(newStatus);
        orderRepository.save(order);

        // Notify customer of status change
        notificationService.sendNotification(
                order.getCustomerEmail(),
                "Order #" + orderId + " status changed from " +
                        oldStatus + " to " + newStatus
        );

        // Log the change
        notificationService.logNotification(
                "Order " + orderId + " status updated to " + newStatus
        );
    }
}
