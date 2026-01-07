package be.kdg.se2.spring.infrastructure.persistence.repositories;

public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository springDataRepo;
    private final OrderMapper mapper;

    public JpaOrderRepositoryAdapter(SpringDataOrderRepository springDataRepo,
                                     OrderMapper mapper) {
        this.springDataRepo = springDataRepo;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity saved = springDataRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return springDataRepo.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Order> findByCustomerEmail(String email) {
        return springDataRepo.findByCustomerEmail(email).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return springDataRepo.findByStatus(status).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return springDataRepo.existsById(id);
    }

    @Override
    public void delete(Order order) {
        springDataRepo.deleteById(order.getId());
    }
}

// ----------------------------------------------------------------------------
