package be.kdg.se2.spring.config;

public class DatabaseConnection {
    private final String connectionId;

    public DatabaseConnection() {
        this.connectionId = "CONN-" + System.currentTimeMillis();
        System.out.println("Creating database connection: " + connectionId);
    }

    public String getConnectionId() {
        return connectionId;
    }
}
