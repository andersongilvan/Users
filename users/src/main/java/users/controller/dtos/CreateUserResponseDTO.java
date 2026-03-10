package users.controller.dtos;

import lombok.Builder;

@Builder
public record CreateUserResponseDTO(
        Long id,
        String name,
        String email
) {
}
