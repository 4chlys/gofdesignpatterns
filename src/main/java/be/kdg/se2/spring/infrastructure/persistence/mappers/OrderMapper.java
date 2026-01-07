package be.kdg.se2.spring.infrastructure.persistence.mappers;

import be.kdg.se2.spring.domain.model.Order;
import be.kdg.se2.spring.infrastructure.persistence.entities.OrderEntity;

public class OrderMapper {

    public OrderEntity toEntity(Order domain) {
        OrderEntity entity = new OrderEntity();
        entity.setId(domain.getId());
        entity.setCustomerEmail(domain.getCustomerEmail());
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreatedAt());
        // Map lines, etc.
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        // Reconstruct domain object from entity
        Order order = new Order(entity.getCustomerEmail());
        // Set other fields using reflection or package-private setters
        return order;
    }
}

// ----------------------------------------------------------------------------
