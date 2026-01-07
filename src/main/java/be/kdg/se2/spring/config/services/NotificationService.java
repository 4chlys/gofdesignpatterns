package be.kdg.se2.spring.config.services;

import be.kdg.se2.spring.config.clients.EmailClient;
import be.kdg.se2.spring.config.clients.SmsClient;

/**
 * Service that uses the configured external library beans.
 */
public class NotificationService {
    private final EmailClient emailClient;
    private final SmsClient smsClient;

    public NotificationService(EmailClient emailClient, SmsClient smsClient) {
        this.emailClient = emailClient;
        this.smsClient = smsClient;
    }

    public void notifyUser(String email, String phone, String message) {
        emailClient.sendEmail(email, "Notification", message);
        smsClient.sendSms(phone, message);
    }
}
