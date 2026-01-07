package be.kdg.se2.spring.infrastructure.services;

import be.kdg.se2.spring.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class DefaultInventoryService implements InventoryService {
    public void reserveItems(Order order) {
        // reservation logic
    }
}

