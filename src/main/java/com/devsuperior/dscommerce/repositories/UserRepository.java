package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("""
    SELECT 
        u.email AS username, 
        u.password AS password, 
        r.id AS roleId, 
        r.authority AS authority
    FROM User u JOIN u.roles r
    WHERE u.email = :email
""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
