package com.ISD.AIMS.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000L;   // 2 giờ
    private static final String SECRET = "my-super-secret-key-that-should-be-at-least-256-bits";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    /** Sinh token — luôn thêm ROLE_ vào mỗi role */
    public String generateToken(String username, Set<String> roles) {

        Set<String> authorities = roles.stream()
                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** Lấy roles an toàn (dù payload trả về List hay Set) */
    public Set<String> extractRoles(String token) {
        Object raw = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");

        if (raw instanceof Collection<?> col) {
            return col.stream().map(Object::toString).collect(Collectors.toSet());
        }
        return Set.of();
    }

    public boolean validateToken(String token) {
        try { Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); return true; }
        catch (JwtException | IllegalArgumentException e) { return false; }
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
