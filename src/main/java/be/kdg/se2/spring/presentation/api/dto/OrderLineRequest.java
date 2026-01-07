package be.kdg.se2.spring.presentation.api.dto;

public record OrderLineRequest(
        @NotNull(message = "Product ID is required")
        UUID productId,

        @Positive(message = "Quantity must be positive")
        @Max(value = 1000, message = "Maximum quantity is 1000")
        int quantity
) {
}
