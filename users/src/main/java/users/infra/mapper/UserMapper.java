package users.infra.mapper;

import org.springframework.stereotype.Component;
import users.http.dtos.*;
import users.infra.entity.User;

import java.util.List;

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

    public UserDetailsResponse toUserDetails(User user) {
        List<AddressResponseDTO> addressResponseDTOList = user.getAddresses().stream()
                .map((address) -> AddressResponseDTO
                        .builder()
                        .id(address.getId())
                        .street(address.getStreet())
                        .number(address.getNumber())
                        .complement(address.getComplement())
                        .city(address.getCity())
                        .neighborhood(address.getNeighborhood())
                        .state(address.getState())
                        .zipCode(address.getZipCode())
                        .username(address.getUser().getEmail())
                        .build()).toList();

        List<ContactResponse> contactResponseList = user.getContacts().stream()
                .map((contact) -> ContactResponse
                        .builder()
                        .id(contact.getId())
                        .phone(contact.getPhone())
                        .username(contact.getUser().getEmail())
                        .build()).toList();

        return UserDetailsResponse
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .addresses(addressResponseDTOList)
                .contacts(contactResponseList)
                .build();

    }

}
