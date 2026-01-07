package be.kdg.se2.spring.application.events;

import be.kdg.se2.spring.domain.model.Order;

public record OrderCreatedEvent(Order order) {
    // Record automatically creates constructor, getters, equals, hashCode
}

// ----------------------------------------------------------------------------
