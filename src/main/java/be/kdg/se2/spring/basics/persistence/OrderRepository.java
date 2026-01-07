package be.kdg.se2.spring.basics.persistence;

import be.kdg.se2.spring.basics.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Repository for Order persistence.
 */
@Repository
public class OrderRepository {

    private final Map<Long, Order> orders = new HashMap<>();
    private Long nextId = 1L;

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(nextId++);
        }
        orders.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> findByCustomerEmail(String email) {
        return orders.values().stream()
                .filter(o -> o.getCustomerEmail().equals(email))
                .toList();
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
