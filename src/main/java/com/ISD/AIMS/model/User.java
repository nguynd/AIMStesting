package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")               // tránh xung đột từ khóa SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled = true;  // mặc định kích hoạt

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;       // ví dụ: ADMIN, USER

    public User() { }

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles    = roles;
        this.enabled  = true;
    }

    /* ---------- Getters & Setters ---------- */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
