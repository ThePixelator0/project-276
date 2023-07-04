package com.etb.eattrainbalance.persistence.repository;

import com.etb.eattrainbalance.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    // Declare a method for finding a user by email. Spring Data JPA will automatically provide the implementation.
    Optional<User> findByEmail (String email);
}
