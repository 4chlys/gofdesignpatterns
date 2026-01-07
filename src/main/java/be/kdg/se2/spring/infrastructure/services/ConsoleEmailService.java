package be.kdg.se2.spring.infrastructure.services;

import be.kdg.se2.spring.domain.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConsoleEmailService implements EmailService {
    public void sendOrderConfirmation(Order order) {
        System.out.println("Email sent for order " + order.getId());
    }
}
