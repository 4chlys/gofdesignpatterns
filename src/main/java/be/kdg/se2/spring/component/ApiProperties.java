package be.kdg.se2.spring.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties with validation.
 * <p>
 * Requires dependency: spring-boot-starter-validation
 */
@Component
@ConfigurationProperties(prefix = "api")
class ApiProperties {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.timeout:5000}")
    private int timeout;

    @Value("${api.retry-attempts:3}")
    private int retryAttempts;

    // Getters
    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }
}
