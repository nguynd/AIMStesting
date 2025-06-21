package com.ISD.AIMS.service;

import com.ISD.AIMS.model.User;
import com.ISD.AIMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(String username, String rawPassword, Set<String> roles) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRoles((roles == null || roles.isEmpty()) ? defaultRoles() : roles);
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    private HashSet<String> defaultRoles() {
        HashSet<String> set = new HashSet<>();
        set.add("USER");
        return set;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void disableUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(false);
            userRepository.save(user);
        });
    }

    public void enableUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void resetPasswordToDefault(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode("123456")); // hoặc sinh ngẫu nhiên
            userRepository.save(user);
        });
    }

    public void updateRoles(Long id, Set<String> roles) {
        userRepository.findById(id).ifPresent(user -> {
            user.setRoles(roles);
            userRepository.save(user);
        });
    }


}
