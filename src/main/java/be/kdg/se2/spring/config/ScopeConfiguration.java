package be.kdg.se2.spring.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Configuration demonstrating different bean scopes.
 */
@Configuration
public class ScopeConfiguration {

    /**
     * SINGLETON scope (default) - one instance for entire application.
     * <p>
     * Use for:
     * - Stateless services
     * - Repositories
     * - Configuration
     * - Caches
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  // This is default, usually omitted
    public DatabaseConnection databaseConnection() {
        return new DatabaseConnection();
    }

    /**
     * PROTOTYPE scope - new instance every time it's requested.
     * <p>
     * Use for:
     * - Stateful objects
     * - Objects with conversation/request scope
     * - When you need independent instances
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ReportGenerator reportGenerator() {
        return new ReportGenerator();
    }
}
