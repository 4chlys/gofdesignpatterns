package be.kdg.se2.spring.shared.config;

import be.kdg.se2.spring.infrastructure.services.ConsoleEmailService;
import be.kdg.se2.spring.infrastructure.services.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevelopmentConfiguration {
    // Development-specific beans

    @Bean
    public EmailService emailService() {
        return new ConsoleEmailService(); // Logs instead of sending
    }
}
