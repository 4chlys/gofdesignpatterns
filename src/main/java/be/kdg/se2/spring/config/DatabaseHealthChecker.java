package be.kdg.se2.spring.config;

public class DatabaseHealthChecker {
    private final DataSource dataSource;

    public DatabaseHealthChecker(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isHealthy() {
        System.out.println("Checking database health for: " + dataSource.getUrl());
        return true; // Simplified
    }
}
