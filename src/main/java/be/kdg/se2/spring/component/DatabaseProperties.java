package be.kdg.se2.spring.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Type-safe configuration properties using @ConfigurationProperties.
 * Preferred for grouped properties.
 * <p>
 * Example application.properties:
 * database.url=jdbc:postgresql://localhost:5432/mydb
 * database.username=dbuser
 * database.password=dbpass
 * database.pool.min-size=5
 * database.pool.max-size=20
 * database.pool.timeout=30000
 */
@Component
@ConfigurationProperties(prefix = "database")
class DatabaseProperties {

    private String url;
    private String username;
    private String password;
    private PoolProperties pool = new PoolProperties();

    // Getters and setters (required for @ConfigurationProperties)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PoolProperties getPool() {
        return pool;
    }

    public void setPool(PoolProperties pool) {
        this.pool = pool;
    }

    /**
     * Nested configuration properties.
     */
    public static class PoolProperties {
        private int minSize = 5;
        private int maxSize = 20;
        private long timeout = 30000;

        public int getMinSize() {
            return minSize;
        }

        public void setMinSize(int minSize) {
            this.minSize = minSize;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }
    }
}
