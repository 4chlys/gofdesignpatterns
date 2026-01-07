package be.kdg.se2.spring.config.clients;

/**
 * Imagine you're using a third-party library for email sending.
 * You can't add @Component to their classes (you don't own the code).
 * <p>
 * Solution: Use @Configuration and @Bean methods.
 */
// This represents a class from an external library (you can't modify it)
public class EmailClient {
    private final String smtpServer;
    private final int port;
    private final String username;

    // No @Component here - it's from a library
    public EmailClient(String smtpServer, int port, String username) {
        this.smtpServer = smtpServer;
        this.port = port;
        this.username = username;
    }

    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via " + smtpServer + ":" + port);
        System.out.println("From: " + username);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }

    public void connect() {
        System.out.println("Connected to SMTP server: " + smtpServer);
    }

    public void disconnect() {
        System.out.println("Disconnected from SMTP server");
    }
}
