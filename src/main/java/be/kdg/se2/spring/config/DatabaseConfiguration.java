package be.kdg.se2.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Real-world example: Configure different databases based on environment.
 */
@Configuration
public class DatabaseConfiguration {

    /**
     * Connection pool configuration from properties.
     */
    @Bean
    public DataSource dataSource(
            @Value("${database.url}") String url,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password,
            @Value("${database.pool.min-size:5}") int minPoolSize,
            @Value("${database.pool.max-size:20}") int maxPoolSize) {

        System.out.println("Configuring DataSource:");
        System.out.println("  URL: " + url);
        System.out.println("  Username: " + username);
        System.out.println("  Pool size: " + minPoolSize + "-" + maxPoolSize);

        DataSource ds = new DataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMinPoolSize(minPoolSize);
        ds.setMaxPoolSize(maxPoolSize);

        return ds;
    }

    /**
     * Bean that depends on DataSource.
     * Spring automatically injects the dataSource bean.
     */
    @Bean
    public DatabaseHealthChecker healthChecker(DataSource dataSource) {
        return new DatabaseHealthChecker(dataSource);
    }
}
