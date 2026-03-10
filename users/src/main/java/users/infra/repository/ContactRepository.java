package users.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.infra.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
