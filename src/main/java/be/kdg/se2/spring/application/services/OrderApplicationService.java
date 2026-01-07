package be.kdg.se2.spring.application.services;

import be.kdg.se2.spring.application.events.OrderCreatedEvent;
import be.kdg.se2.spring.domain.model.Order;
import be.kdg.se2.spring.domain.model.OrderLine;
import be.kdg.se2.spring.domain.repositories.OrderRepository;
import be.kdg.se2.spring.domain.services.PricingService;
import be.kdg.se2.spring.presentation.api.dto.OrderLineRequest;
import be.kdg.se2.spring.presentation.api.exception.OrderNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final PricingService pricingService;
    private final ApplicationEventPublisher eventPublisher;

    // Constructor injection - Spring provides dependencies
    public OrderApplicationService(
            OrderRepository orderRepository,
            PricingService pricingService,
            ApplicationEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.pricingService = pricingService;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Use Case: Create new order
     * - Validates input
     * - Creates domain object
     * - Persists
     * - Publishes event
     */
    public UUID createOrder(String customerEmail, List<OrderLineRequest> items) {
        // Create domain object
        Order order = new Order(customerEmail);

        // Add lines with business logic
        items.forEach(item ->
                order.addLine(new OrderLine(item.productId(), item.quantity()))
        );

        // Persist
        Order savedOrder = orderRepository.save(order);

        // Publish domain event
        eventPublisher.publishEvent(new OrderCreatedEvent(savedOrder));

        return savedOrder.getId();
    }

    /**
     * Use Case: Confirm order
     * - Loads order
     * - Applies business rule
     * - Persists changes
     */
    public void confirmOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.confirm();
        orderRepository.save(order);
    }

    /**
     * Query: Get order details
     * @Transactional(readOnly = true) optimizes for reads
     */
    @Transactional(readOnly = true)
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    @Transactional(readOnly = true)
    public List<Order> getCustomerOrders(String customerEmail) {
        return orderRepository.findByCustomerEmail(customerEmail);
    }
}

// ----------------------------------------------------------------------------
