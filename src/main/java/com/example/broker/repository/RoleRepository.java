package com.example.broker.repository;

import com.example.broker.repository.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAll();

    @Query(
            value = "SELECT name " +
                    "FROM roles " +
                    "WHERE id = ( SELECT role_id FROM users_roles WHERE user_id = :userId )", nativeQuery = true
    )
    List<String> findRolesByUserId(@Param("userId") Long userId);
}
