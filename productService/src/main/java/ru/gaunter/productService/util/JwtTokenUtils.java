package ru.gaunter.productService.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    private Key key;

    private void initKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(UserDetails userDetails, String email, String phone, List<String> roles) {
        if (key == null) {
            initKey();
        }

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtLifetime.toMillis()))
                .claim("email", email)
                .claim("phone", phone)
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            if (key == null) {
                initKey();
            }

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).get("email", String.class);
    }

    public String getPhoneFromToken(String token) {
        return getAllClaimsFromToken(token).get("phone", String.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        if (key == null) {
            initKey();
        }

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UUID extractUserUuid(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return UUID.fromString(claims.get("uuid", String.class));
    }
}
