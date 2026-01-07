// ============================================================================
// LEVEL 1: FOUNDATION - Understanding Basic Dependency Injection
// ============================================================================
// Location: src/main/java/be/kdg/se2/spring/basics/
// 
// Learning Objectives:
// 1. Understand what dependency injection means
// 2. Learn the difference between tight coupling and loose coupling
// 3. Master constructor injection (the recommended approach)
// 4. See how Spring manages object creation and dependencies
// ============================================================================

package be.kdg.se2.spring.basics.services;

import org.springframework.stereotype.Component;

/**
 * The simplest Spring component - a standalone service with no dependencies.
 *
 * Key Points:
 * - @Component tells Spring to create and manage an instance
 * - Spring creates exactly ONE instance (singleton by default)
 * - Other public classes can request this via dependency injection
 */
@Component
public class NotificationService {

    public void sendNotification(String recipient, String message) {
        System.out.println("Notification to " + recipient + ": " + message);
    }

    public void logNotification(String message) {
        System.out.println("[LOG] " + message);
    }
}
