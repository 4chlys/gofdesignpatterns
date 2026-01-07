package be.kdg.se2.spring.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Bean with explicit lifecycle management using annotations.
 * <p>
 * Alternative to initMethod/destroyMethod in @Bean
 */
@Component
public class CacheManager {

    private Map<String, Object> cache;

    /**
     * @PostConstruct runs after dependency injection is complete.
     * <p>
     * Use for:
     * - Initializing resources
     * - Loading configuration
     * - Starting background threads
     * - Validating state
     */
    @PostConstruct
    public void initialize() {
        System.out.println("Initializing cache at " + LocalDateTime.now());
        cache = new java.util.HashMap<>();
        loadInitialData();
    }

    private void loadInitialData() {
        cache.put("config", "loaded");
        System.out.println("Cache initialized with " + cache.size() + " entries");
    }

    /**
     * @PreDestroy runs before bean is destroyed (application shutdown).
     * <p>
     * Use for:
     * - Closing connections
     * - Saving state
     * - Releasing resources
     * - Stopping background threads
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up cache at " + LocalDateTime.now());
        if (cache != null) {
            System.out.println("Clearing " + cache.size() + " cache entries");
            cache.clear();
        }
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }
}
