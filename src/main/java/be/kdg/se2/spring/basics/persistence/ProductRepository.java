package be.kdg.se2.spring.basics.persistence;

import be.kdg.se2.spring.basics.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Repository manages data persistence.
 *
 * @Repository is a specialized @Component that:
 * - Indicates this is a data access layer component
 * - Enables automatic exception translation
 * - Makes intent clear to other developers
 * <p>
 * In real applications, this would use JPA/JDBC to access a database.
 * Here we use in-memory storage for simplicity.
 */
@Repository
public class ProductRepository {

    // Simulated database - in reality, this would be an actual database
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public ProductRepository() {
        // Initialize with some sample data
        save(new Product(null, "Laptop", 999.99, 10));
        save(new Product(null, "Mouse", 29.99, 50));
        save(new Product(null, "Keyboard", 79.99, 30));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product = new Product(nextId++, product.getName(),
                    product.getPrice(), product.getStockQuantity());
        }
        products.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> findByNameContaining(String name) {
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
}
