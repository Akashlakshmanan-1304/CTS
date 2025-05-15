package com.example.HAS.service;

import com.example.HAS.entity.User;
import com.example.HAS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);
    }
}
