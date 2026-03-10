package users.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;
import users.infra.entity.User;

import java.util.Optional;


@ResponseBody
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Boolean> existsByEmail(String email);

    Optional<User> findByEmail(String email);

}
