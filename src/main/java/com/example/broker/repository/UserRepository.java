package com.example.broker.repository;

import com.example.broker.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

    User findByFirstName(String name);

    User findByUsername(String email);

}
