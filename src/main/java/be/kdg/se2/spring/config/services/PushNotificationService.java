package be.kdg.se2.spring.config.services;

public class PushNotificationService {
    public void sendPushNotification(String userId, String message) {
        System.out.println("Push notification to " + userId + ": " + message);
    }
}
