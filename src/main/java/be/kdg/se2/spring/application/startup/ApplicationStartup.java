package be.kdg.se2.spring.application.startup;

import be.kdg.se2.spring.application.services.OrderApplicationService;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    private final OrderApplicationService orderService;

    public ApplicationStartup(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    public void run() {
        // optional startup logic
        // orderService.createOrder(...)
    }
}
