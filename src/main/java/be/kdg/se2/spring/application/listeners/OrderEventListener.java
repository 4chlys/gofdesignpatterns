package be.kdg.se2.spring.application.listeners;

import be.kdg.se2.spring.application.events.OrderCreatedEvent;
import be.kdg.se2.spring.infrastructure.services.EmailService;
import be.kdg.se2.spring.infrastructure.services.InventoryService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class OrderEventListener {

    private final EmailService emailService;
    private final InventoryService inventoryService;

    public OrderEventListener(EmailService emailService,
                              InventoryService inventoryService) {
        this.emailService = emailService;
        this.inventoryService = inventoryService;
    }

    /**
     * @EventListener marks method as event handler
     * @Async runs in separate thread (requires @EnableAsync)
     */
    @EventListener
    @Async
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Send confirmation email
        emailService.sendOrderConfirmation(event.order());

        // Reserve inventory
        inventoryService.reserveItems(event.order());
    }
}