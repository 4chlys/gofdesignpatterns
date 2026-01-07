package be.kdg.se2.spring.presentation.api.mappers;

public class OrderDtoMapper {

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerEmail(),
                order.getLines().stream()
                        .map(this::toLineResponse)
                        .toList(),
                order.getStatus().name(),
                order.getTotalAmount().getAmount(),
                order.getTotalAmount().getCurrency().getCurrencyCode(),
                order.getCreatedAt()
        );
    }

    private OrderLineResponse toLineResponse(OrderLine line) {
        return new OrderLineResponse(
                line.getProductName(),
                line.getQuantity(),
                line.getUnitPrice().getAmount(),
                line.getLineTotal().getAmount()
        );
    }
}

// ----------------------------------------------------------------------------
