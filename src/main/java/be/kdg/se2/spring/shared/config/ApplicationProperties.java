package be.kdg.se2.spring.shared.config;

public class ApplicationProperties {

    private String name;
    private EmailProperties email = new EmailProperties();
    private SecurityProperties security = new SecurityProperties();

    public static class EmailProperties {
        private String from;
        private String host;
        private int port = 587;

        // Getters and setters
    }

    public static class SecurityProperties {
        private boolean enabled = true;
        private String jwtSecret;

        // Getters and setters
    }

    // Getters and setters
}

// ============================================================================
// 6. CONFIGURATION EXAMPLES
// ============================================================================
