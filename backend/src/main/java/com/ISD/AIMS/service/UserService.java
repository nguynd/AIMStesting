package com.ISD.AIMS.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ISD.AIMS.model.User;
import com.ISD.AIMS.repository.UserRepository;

@Service
public class UserService implements UserDetailsService { 

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
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
            user.setPassword(passwordEncoder.encode("123456"));
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
