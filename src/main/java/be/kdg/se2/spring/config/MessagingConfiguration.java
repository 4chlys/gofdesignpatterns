package be.kdg.se2.spring.config;

import be.kdg.se2.spring.config.clients.EmailClient;
import be.kdg.se2.spring.config.clients.SmsClient;
import be.kdg.se2.spring.config.conditions.AdvancedNotificationsCondition;
import be.kdg.se2.spring.config.services.NotificationService;
import be.kdg.se2.spring.config.services.PushNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration tells Spring this class contains bean definitions.
 * <p>
 * Use @Configuration when:
 * - Working with third-party libraries (can't add @Component to their classes)
 * - Need complex initialization logic
 * - Want explicit control over bean creation
 * - Creating multiple related beans together
 * @Bean methods:
 * - Method name becomes bean name (unless specified otherwise)
 * - Return type is the bean type
 * - Spring calls the method once (for singleton) and manages the result
 */
@Configuration
public class MessagingConfiguration {

    /**
     * Simple @Bean definition.
     * <p>
     * Key points:
     * - Method name "emailClient" becomes the bean name
     * - Spring calls this method once and stores the result
     * - Properties injected from application.properties via @Value
     */
    @Bean
    public EmailClient emailClient(
            @Value("${email.smtp.server:smtp.gmail.com}") String smtpServer,
            @Value("${email.smtp.port:587}") int port,
            @Value("${email.username:noreply@company.com}") String username) {

        System.out.println("Creating EmailClient bean...");
        return new EmailClient(smtpServer, port, username);
    }

    /**
     * Bean with custom name.
     * <p>
     * Access this bean as "smsService" not "smsClient"
     */
    @Bean(name = "smsService")
    public SmsClient smsClient(
            @Value("${sms.api.key:dummy-key}") String apiKey,
            @Value("${sms.api.url:https://api.sms.com}") String apiUrl) {

        System.out.println("Creating SmsClient bean...");
        return new SmsClient(apiKey, apiUrl);
    }

    /**
     * Bean with lifecycle callbacks.
     * <p>
     * initMethod: called after bean is fully initialized
     * destroyMethod: called when application shuts down
     * <p>
     * Use for:
     * - Opening connections
     * - Loading data
     * - Cleanup on shutdown
     */
    @Bean(initMethod = "connect", destroyMethod = "disconnect")
    public EmailClient managedEmailClient(
            @Value("${email.smtp.server:smtp.gmail.com}") String smtpServer,
            @Value("${email.smtp.port:587}") int port,
            @Value("${email.username:noreply@company.com}") String username) {

        return new EmailClient(smtpServer, port, username);
    }

    /**
     * Bean that depends on other beans - method parameter injection.
     * <p>
     * Spring automatically provides the EmailClient and SmsClient parameters.
     * This is the SAME as constructor injection in @Component classes.
     */
    @Bean
    public NotificationService notificationService(EmailClient emailClient,
                                                   SmsClient smsClient) {
        System.out.println("Creating NotificationService with injected dependencies...");
        return new NotificationService(emailClient, smsClient);
    }

    /**
     * Conditional bean - only created if condition is true.
     * <p>
     * This bean only exists when feature.advanced-notifications=true
     * in application.properties
     */
    @Bean
    @Conditional(AdvancedNotificationsCondition.class)
    public PushNotificationService pushNotificationService() {
        return new PushNotificationService();
    }
}
