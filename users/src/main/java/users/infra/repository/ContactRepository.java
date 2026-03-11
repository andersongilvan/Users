package users.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import users.infra.entity.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("""
            SELECT c FROM Contact c
            WHERE c.user.email = :username
            """)
    List<Contact> findAllByUser(@Param("username") String username);

    @Query("""
             SELECT c FROM Contact c
             WHERE c.id = :contactId
             AND c.user.email = :username
            """)
    Optional<Contact> findOne(@Param("contactId") Long contactId, @Param("username") String username);


    @Modifying
    @Query("""
            DELETE FROM Contact c
             WHERE c.id = :id
             AND c.user.email = :username
            """
    )
    void delete(@Param("id") Long id, @Param("username") String username);
}
