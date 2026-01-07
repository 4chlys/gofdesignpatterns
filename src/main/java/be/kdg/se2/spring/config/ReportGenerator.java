package be.kdg.se2.spring.config;

public class ReportGenerator {
    private final String sessionId;

    public ReportGenerator() {
        this.sessionId = "SESSION-" + System.currentTimeMillis();
        System.out.println("Creating report generator: " + sessionId);
    }

    public String generateReport() {
        return "Report from " + sessionId;
    }
}
