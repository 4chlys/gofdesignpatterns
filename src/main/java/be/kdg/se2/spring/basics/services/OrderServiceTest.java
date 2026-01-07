package be.kdg.se2.spring.basics.services;

import be.kdg.se2.spring.basics.persistence.OrderRepository;
import be.kdg.se2.spring.basics.persistence.ProductRepository;

/**
 * This would be in your test public class.
 * Notice: NO SPRING needed for unit testing!
 */
public class OrderServiceTest {

    // Example test method (not runnable here, just for illustration)
    public void testCreateOrder() {
        // Create mock dependencies (using Mockito or similar)
        OrderRepository mockOrderRepo = createMock();
        ProductRepository mockProductRepo = createMock();
        NotificationService mockNotificationService = createMock();

        // Create service with mock dependencies - easy with constructor injection!
        OrderService service = new OrderService(
                mockOrderRepo,
                mockProductRepo,
                mockNotificationService
        );

        // Now you can test the service in isolation
        // You can control what the mocks return
        // You can verify what methods were called on the mocks
    }

    private <T> T createMock() {
        // This would use Mockito.mock() in real tests
        return null;
    }
}
