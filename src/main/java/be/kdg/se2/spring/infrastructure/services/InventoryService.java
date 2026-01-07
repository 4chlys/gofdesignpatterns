package be.kdg.se2.spring.infrastructure.services;

import be.kdg.se2.spring.domain.model.Order;

public interface InventoryService {
    void reserveItems(Order order);
}
