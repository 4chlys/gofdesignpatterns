package be.kdg.se2.spring.domain.repositories;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID id);
    List<Order> findByCustomerEmail(String email);
    List<Order> findByStatus(OrderStatus status);
    boolean existsById(UUID id);
    void delete(Order order);
}

// ----------------------------------------------------------------------------
