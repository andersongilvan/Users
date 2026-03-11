package users.http.dtos;

import lombok.Builder;

@Builder
public record ContactResponse(
        Long id,
        String phone,
        String username
) {
}
