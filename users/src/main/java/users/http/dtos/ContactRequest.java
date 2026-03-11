package users.http.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record ContactRequest(
        @NotBlank(message = "The phone number is required.")
        @Pattern(regexp = "^\\d{1,11}$",
                message = " The value must contain only numbers and a maximum of 11 digits.")
        String phone
) {
}
