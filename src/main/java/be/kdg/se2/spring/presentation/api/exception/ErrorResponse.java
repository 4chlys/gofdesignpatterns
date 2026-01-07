package be.kdg.se2.spring.presentation.api.exception;

record ErrorResponse(int status, String message, LocalDateTime timestamp) {
}
