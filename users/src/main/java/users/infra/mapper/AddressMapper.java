package users.infra.mapper;

import org.springframework.stereotype.Component;
import users.http.dtos.CreateAddressDTO;
import users.http.dtos.AddressResponseDTO;
import users.infra.entity.Address;

@Component
public class AddressMapper {

    public Address toEntity(CreateAddressDTO dto) {
        return Address
                .builder()
                .street(dto.street())
                .number(dto.number())
                .complement(dto.complement())
                .city(dto.city())
                .neighborhood(dto.neighborhood())
                .state(dto.state())
                .zipCode(dto.zipCode())
                .build();
    }

    public AddressResponseDTO toAddressResponse(Address address) {
        return AddressResponseDTO
                .builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .username(address.getUser().getUsername())
                .build();
    }



}
