package com.example.converteraudio.controller;


import com.example.converteraudio.model.User;
import com.example.converteraudio.service.UserService;
import com.example.converteraudio.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User request) {

        User user = userService.register(request);

        user.setPassword(null);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");

        String token = userService.login(email, password);

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {

        String userId = authentication.getName();

        User user = userService.getById(userId);
        user.setPassword(null);

        return ResponseEntity.ok(user);
    }
}
