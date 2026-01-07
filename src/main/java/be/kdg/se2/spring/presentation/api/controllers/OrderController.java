package be.kdg.se2.spring.presentation.api.controllers;

import be.kdg.se2.spring.application.services.OrderApplicationService;
import be.kdg.se2.spring.domain.model.Order;
import be.kdg.se2.spring.presentation.api.dto.CreateOrderRequest;
import be.kdg.se2.spring.presentation.api.dto.OrderResponse;
import be.kdg.se2.spring.presentation.api.mappers.OrderDtoMapper;
import jakarta.validation.Valid;

import java.util.UUID;

public class OrderController {

    private final OrderApplicationService orderService;
    private final OrderDtoMapper dtoMapper;

    public OrderController(OrderApplicationService orderService,
                           OrderDtoMapper dtoMapper) {
        this.orderService = orderService;
        this.dtoMapper = dtoMapper;
    }

    /**
     * Create new order
     * @Valid triggers validation on CreateOrderRequest
     * @RequestBody deserializes JSON to Java object
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        UUID orderId = orderService.createOrder(
                request.customerEmail(),
                request.items()
        );

        Order order = orderService.getOrder(orderId);
        OrderResponse response = dtoMapper.toResponse(order);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Get order by ID
     * @PathVariable extracts {id} from URL
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(dtoMapper.toResponse(order));
    }

    /**
     * Get customer orders
     * @RequestParam extracts query parameter
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getCustomerOrders(
            @RequestParam String customerEmail) {

        List<Order> orders = orderService.getCustomerOrders(customerEmail);
        List<OrderResponse> responses = orders.stream()
                .map(dtoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    /**
     * Confirm order
     * @PutMapping for updates
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmOrder(@PathVariable UUID id) {
        orderService.confirmOrder(id);
        return ResponseEntity.noContent().build();
    }
}

// ----------------------------------------------------------------------------
