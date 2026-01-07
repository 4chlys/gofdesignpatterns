package be.kdg.se2.spring.infrastructure.services;

import be.kdg.se2.spring.domain.model.Order;

public interface EmailService {
    void sendOrderConfirmation(Order order);
}
