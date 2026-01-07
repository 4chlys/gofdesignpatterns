package be.kdg.se2.spring.infrastructure.persistence.repositories;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, UUID> {

    // Method name query - Spring generates SQL
    List<OrderEntity> findByCustomerEmail(String email);

    List<OrderEntity> findByStatus(OrderStatus status);

    // Custom JPQL query
    @Query("SELECT o FROM OrderEntity o WHERE o.customerEmail = :email AND o.status = :status")
    List<OrderEntity> findByEmailAndStatus(String email, OrderStatus status);

    // Native SQL query
    @Query(value = "SELECT * FROM orders WHERE created_at > :date", nativeQuery = true)
    List<OrderEntity> findRecentOrders(LocalDateTime date);
}

// ----------------------------------------------------------------------------
