package users.controller.dtos;

import lombok.Builder;

@Builder
public record CreateAddressDTO(
        String street,
        Long number,
        String complement,
        String city,
        String neighborhood,
        String state,
        String zipCode
) {
}
