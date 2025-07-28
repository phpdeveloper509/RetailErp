package com.retail.erp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.erp.model.Role;
import com.retail.erp.model.User;
import com.retail.erp.repository.UserRepository;
import com.retail.erp.security.JwtTokenProvider;
import com.retail.erp.service.SessionService;

import lombok.AllArgsConstructor;
import lombok.Data;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        System.out.println(passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword()));
        if (optionalUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(request.getEmail());
        sessionService.updateSession(token);

        return ResponseEntity.ok(new AuthResponse(token, optionalUser.get().getRole().toString()));
    }


    @PostMapping("/register") // Optional: Admin adds cashier
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        Role role = Role.CASHIER;
        if(request.getRole().equalsIgnoreCase("ADMIN")) {
        	role= Role.ADMIN;
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .mobile(request.getMobile())
                .name(request.getName())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

    @Data
    static class AuthRequest {
        private String email;
        private String password;
        private String role;
        private String mobile;
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class AuthResponse {
        private String token;
        private String role;
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String oldToken = authHeader.substring(7);
            if (sessionService.isActive(oldToken) && !jwtTokenProvider.validateToken(oldToken)) {
                String username = jwtTokenProvider.getUsernameFromToken(oldToken);
                String newToken = jwtTokenProvider.generateToken(username);
                sessionService.updateSession(newToken);
                return ResponseEntity.ok(new AuthResponse(newToken, "refreshed"));
            } else {
                return ResponseEntity.status(401).body("Session expired or invalid");
            }
        }
        return ResponseEntity.badRequest().body("Missing token");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            sessionService.invalidate(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

}
