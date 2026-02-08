package com.example.converteraudio.service;


import com.example.converteraudio.exception.ResourceNotFoundException;
import com.example.converteraudio.model.User;
import com.example.converteraudio.repository.UserRepository;
import com.google.cloud.Timestamp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(User request) {

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(Timestamp.now());

        return userRepository.save(user);
    }

    public String login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }

    public User getById(String id) {

        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        return user;
    }

    public User getCurrentUser(String userIdFromToken) {
        return getById(userIdFromToken);
    }
}
