package be.kdg.se2.spring.infrastructure.services;

import be.kdg.se2.spring.domain.model.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class SmtpEmailService implements EmailService {
    public void sendOrderConfirmation(Order order) {
        // real SMTP implementation
    }
}

