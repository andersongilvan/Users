package users.http.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record CreateUserDTO(
        @NotBlank
        @Length(min = 1, max = 20)
        String name,

        @Email
        String email,

        @NotBlank
        @Length(min = 6, max = 10)
        String password

) {
}
