package be.kdg.se2.spring.presentation.api.dto;

public record OrderResponse(
        UUID id,
        String customerEmail,
        List<OrderLineResponse> lines,
        String status,
        BigDecimal totalAmount,
        String currency,
        LocalDateTime createdAt
) {}

// ----------------------------------------------------------------------------
