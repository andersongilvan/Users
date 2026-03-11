package users.business.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import users.infra.entity.User;
import users.infra.exceptions.ConflictException;
import users.infra.exceptions.NotFoundException;
import users.infra.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {

        userRepository.existsByEmail(user.getEmail())
                .orElseThrow(() -> new ConflictException("E-mail already exists"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findBEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
