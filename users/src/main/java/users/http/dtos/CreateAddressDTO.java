package users.http.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record CreateAddressDTO(
        @NotBlank()
        @Length(min = 1, max = 20)
        String street,

        @NotNull
        Long number,

        @NotBlank
        @Length(min = 1, max = 50)
        String complement,

        @NotBlank
        @Length(min = 1, max = 20)
        String city,

        @NotBlank
        @Length(min = 1, max = 20)
        String neighborhood,

        @NotBlank
        @Length(min = 1, max = 20)
        String state,

        @NotBlank
        @Pattern(regexp = "^\\d{5}-?\\d{3}$",
                message = "Invalid zipCode format. Use 12345-678 or 12345678.")
        String zipCode
) {
}
