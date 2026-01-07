package be.kdg.se2.spring.shared.config;

import be.kdg.se2.spring.infrastructure.services.EmailService;
import be.kdg.se2.spring.infrastructure.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProductionConfiguration {
    // Production-specific beans

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService(); // Actually sends emails
    }
}
