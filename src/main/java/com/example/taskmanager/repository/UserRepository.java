package com.example.taskmanager.repository;

import com.example.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring reads "findByEmail" and generates:
    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);

    // Spring reads "existsByEmail" and generates:
    // SELECT COUNT(*) > 0 FROM users WHERE email = ?
    boolean existsByEmail(String email);
}