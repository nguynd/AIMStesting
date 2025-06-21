package com.ISD.AIMS.controller;

import com.ISD.AIMS.model.User;
import com.ISD.AIMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/users/{id}/disable")
    public ResponseEntity<?> disable(@PathVariable Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok("User disabled");
    }

    @PutMapping("/users/{id}/enable")
    public ResponseEntity<?> enable(@PathVariable Long id) {
        userService.enableUser(id);
        return ResponseEntity.ok("User enabled");
    }

    @PutMapping("/users/{id}/reset-password")
    public ResponseEntity<?> resetPassword(@PathVariable Long id) {
        userService.resetPasswordToDefault(id);
        return ResponseEntity.ok("Password reset to default");
    }

    @PutMapping("/users/{id}/roles")
    public ResponseEntity<?> updateRoles(@PathVariable Long id, @RequestBody Set<String> roles) {
        userService.updateRoles(id, roles);
        return ResponseEntity.ok("Roles updated");
    }
}
