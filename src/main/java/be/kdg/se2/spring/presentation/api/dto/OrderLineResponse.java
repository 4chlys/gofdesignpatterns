package be.kdg.se2.spring.presentation.api.dto;

public record OrderLineResponse(
        String productName,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal lineTotal
) {
}
