package com.ISD.AIMS.controller;

import com.ISD.AIMS.model.User;
import com.ISD.AIMS.security.JwtUtil;
import com.ISD.AIMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public static class RegisterRequest {
        public String username;
        public String password;
        public Set<String> roles;
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean created = userService.register(
                request.username,
                request.password,
                request.roles
        );

        if (!created) {
            return ResponseEntity
                    .badRequest()
                    .body("Username already exists.");
        }

        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userService.findByUsername(request.username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        User user = userOpt.get();

        if (!user.isEnabled()) {
            return ResponseEntity.status(403).body("Account is disabled");
        }

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "username", user.getUsername(),
                "roles", user.getRoles()
        ));
    }
}
