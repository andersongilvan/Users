package users.http.dtos;

import lombok.Builder;

@Builder
public record CreateUserDTO(
        String name,
        String email,
        String password

) {
}
