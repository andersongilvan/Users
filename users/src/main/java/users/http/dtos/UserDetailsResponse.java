package users.http.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDetailsResponse(
        Long id,
        String name,
        String email,
        List<AddressResponseDTO> addresses,
        List<ContactResponse> contacts
) {
}
