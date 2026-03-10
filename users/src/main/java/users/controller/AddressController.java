package users.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import users.business.AddressService;
import users.controller.dtos.AddressResponseDTO;
import users.controller.dtos.CreateAddressDTO;
import users.infra.entity.Address;
import users.infra.mapper.AddressMapper;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> create(
            @RequestBody CreateAddressDTO dto,
            @AuthenticationPrincipal(expression = "username") String email) {

        Address address = addressMapper.toEntity(dto);

        Address result = addressService.create(address, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressMapper.toAddressResponse(result));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> list(
            @AuthenticationPrincipal(expression = "username") String email) {

        List<Address> allByUser = addressService.findAllByUser(email);

        List<AddressResponseDTO> result = allByUser.stream()
                .map(addressMapper::toAddressResponse).toList();

        return ResponseEntity.ok(result);

    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> update(
            @AuthenticationPrincipal(expression = "username") String email,
            @RequestBody CreateAddressDTO dto, @PathVariable Long addressId
    ) {
        Address address = addressMapper.toEntity(dto);

        Address result = addressService.update(address, email, addressId);

        return ResponseEntity.ok(addressMapper.toAddressResponse(result));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal(expression = "username") String email, @PathVariable Long addressId) {

        addressService.delete(addressId, email);

        return ResponseEntity.noContent().build();
    }

}
