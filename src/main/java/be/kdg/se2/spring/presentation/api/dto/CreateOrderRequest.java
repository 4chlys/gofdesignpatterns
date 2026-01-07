package be.kdg.se2.spring.presentation.api.dto;

public record CreateOrderRequest(

        @NotBlank(message = "Customer email is required")
        @Email(message = "Invalid email format")
        String customerEmail,

        @NotEmpty(message = "Order must have at least one item")
        @Size(max = 100, message = "Maximum 100 items per order")
        List<OrderLineRequest> items
) {
    // Record creates constructor, getters, equals, hashCode automatically
}

// ----------------------------------------------------------------------------
