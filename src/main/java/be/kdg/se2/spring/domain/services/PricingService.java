package be.kdg.se2.spring.domain.services;

import be.kdg.se2.spring.domain.model.Order;
import be.kdg.se2.spring.domain.model.*;
import be.kdg.se2.spring.domain.valueobjects.Money;

public class PricingService {

    public Money calculateDiscount(Order order, Customer customer) {
        if (customer.isVIP() && order.getTotalAmount().isGreaterThan(Money.of(1000))) {
            return order.getTotalAmount().multiply(0.1);
        }
        return Money.of(0);
    }

    public Money calculateShipping(Order order, Address address) {
        return Money.of(10);
    }
}
