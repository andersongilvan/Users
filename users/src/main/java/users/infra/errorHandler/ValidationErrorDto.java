package users.infra.errorHandler;

public record ValidationErrorDto(
    String field,
    String message
) {
}
