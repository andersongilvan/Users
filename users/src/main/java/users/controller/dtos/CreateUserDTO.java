package users.controller.dtos;

import lombok.Builder;

@Builder
public record CreateUserDTO(
        String name,
        String email,
        String password

) {
}
