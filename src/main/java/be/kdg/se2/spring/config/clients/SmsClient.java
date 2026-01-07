package be.kdg.se2.spring.config.clients;

// Another external library class
public class SmsClient {
    private final String apiKey;
    private final String apiUrl;

    public SmsClient(String apiKey, String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public void sendSms(String phoneNumber, String message) {
        System.out.println("Sending SMS via " + apiUrl);
        System.out.println("To: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}
