package com.example.converteraudio.controller;

import com.example.converteraudio.dto.AuthRequest;
import com.example.converteraudio.dto.AuthResponse;
import com.example.converteraudio.dto.RegisterRequest;
import com.example.converteraudio.model.User;
import com.example.converteraudio.security.JwtUtil;
import com.example.converteraudio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        User user = userService.register(req.getEmail(), req.getPassword(), req.getFullName());
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
            String token = jwtUtil.generateToken(req.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/reset-request")
    public ResponseEntity<?> resetRequest(@RequestParam String email) {
        userService.createPasswordResetToken(email);
        return ResponseEntity.ok("If that email exists, a reset token was sent");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestParam String token, @RequestParam String newPassword) {
        boolean ok = userService.resetPassword(token, newPassword);
        if (ok) return ResponseEntity.ok("Password reset");
        return ResponseEntity.badRequest().body("Invalid or expired token");
    }
}
