package users.infra.mapper;

import org.springframework.stereotype.Component;
import users.controller.dtos.CreateUserDTO;
import users.controller.dtos.CreateUserResponseDTO;
import users.infra.entity.User;

@Component
public class UserMapper {

    public User toEntity(CreateUserDTO dto) {
        return User
                .builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public CreateUserResponseDTO toCreateUserDtoResponse(User user) {
        return CreateUserResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
