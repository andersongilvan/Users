package users.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.infra.entity.User;
import users.infra.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // busca o usuario no banco de dados pelo email
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //  cria e retorna um objeto UserDetails com base no usuario encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // define o nome do usuario como o email
                .password(user.getPassword()) // define a senha do usuario
                .build(); // constroi o objeto UserDetails
    }
}
