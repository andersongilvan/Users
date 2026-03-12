package users.http.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserAuthDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
