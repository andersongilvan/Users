package users.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import users.infra.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("""
            SELECT a FROM Address a WHERE a.user.email = :email
            """)
    List<Address> findAllByEmail(@Param("email") String email);

    @Modifying
    @Query("""
            DELETE FROM Address a
             WHERE a.id = :id
             AND a.user.email = :username
            """
    )
    void delete(@Param("id") Long id, @Param("username") String username);

}
