package users.http.dtos;

import jakarta.validation.constraints.NotNull;

public record RequestId(@NotNull Long id) {
}
