package be.kdg.se2.spring.domain.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceConfiguration {

    @Bean
    public PricingService pricingService() {
        return new PricingService();
    }
}

