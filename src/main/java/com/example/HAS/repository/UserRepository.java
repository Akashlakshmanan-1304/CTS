package com.example.HAS.repository;

import com.example.HAS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(String doctor);

    User findByNameAndRole(String doctorName, String doctor);
}
