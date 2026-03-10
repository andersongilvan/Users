package users.business;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.infra.entity.Address;
import users.infra.entity.User;
import users.infra.exceptions.NotFoundException;
import users.infra.repository.AddressRepository;
import users.infra.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;


    public Address create(Address address, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        address.setCity(NormalizeUtils.normalizeString(address.getCity()));
        address.setStreet(NormalizeUtils.normalizeString(address.getStreet()));
        address.setComplement(NormalizeUtils.normalizeString(address.getComplement()));
        address.setNeighborhood(NormalizeUtils.normalizeString(address.getNeighborhood()));
        address.setState(NormalizeUtils.normalizeString(address.getState()));
        address.setZipCode(NormalizeUtils.normalizeCep(address.getZipCode()));

        address.setUser(user);

        return addressRepository.save(address);
    }

    public List<Address> findAllByUser(String username) {

        userRepository.existsByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return addressRepository.findAllByEmail(username);
    }

    public Address update(Address address, String username, Long addressId) {

        userRepository.existsByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found."));

        Address addressExists = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found"));

        if (!addressExists.getUser().getEmail().equals(username)) {
            throw new NotFoundException("User unauthorized.");
        }

        addressExists.setCity(NormalizeUtils.normalizeString(address.getCity()));
        addressExists.setStreet(NormalizeUtils.normalizeString(address.getStreet()));
        addressExists.setComplement(NormalizeUtils.normalizeString(address.getComplement()));
        addressExists.setNeighborhood(NormalizeUtils.normalizeString(address.getNeighborhood()));
        addressExists.setState(NormalizeUtils.normalizeString(address.getState()));
        addressExists.setZipCode(NormalizeUtils.normalizeCep(address.getZipCode()));

        return addressRepository.save(addressExists);

    }

    @Transactional
    public void delete(Long addressId, String username) {

        userRepository.existsByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        addressRepository.delete(addressId, username);

    }
}
