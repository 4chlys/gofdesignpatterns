package be.kdg.se2.spring.config;

// Supporting classes for database example
public class DataSource {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;

    // Getters and setters
    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMinPoolSize(int size) {
        this.minPoolSize = size;
    }

    public void setMaxPoolSize(int size) {
        this.maxPoolSize = size;
    }

    public String getUrl() {
        return url;
    }
}
