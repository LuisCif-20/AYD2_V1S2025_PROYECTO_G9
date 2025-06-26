package com.ayd2.imporcomgua.services.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.properties.jwt.JwtProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(String email) {
        return generateToken(
                email,
                jwtProperties.getAccess().getSecretKey(),
                jwtProperties.getAccess().getExpirationTime());
    }

    @Override
    public String generateRefreshToken(String email) {
        return generateToken(
                email,
                jwtProperties.getRefresh().getSecretKey(),
                jwtProperties.getRefresh().getExpirationTime());
    }

    @Override
    public boolean validateAccessToken(String token) {
        return validateToken(token, jwtProperties.getAccess().getSecretKey());
    }

    @Override
    public boolean validateRefreshToken(String token) {
        return validateToken(token, jwtProperties.getRefresh().getSecretKey());
    }

    @Override
    public String getEmailFromAccessToken(String token) {
        return getEmailFromToken(token, jwtProperties.getAccess().getSecretKey());
    }

    @Override
    public String getEmailFromRefreshToken(String token) {
        return getEmailFromToken(token, jwtProperties.getRefresh().getSecretKey());
    }

    private String generateToken(String email, String secretKey, long expirationTime) {
        final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    private boolean validateToken(String token, String secretKey) {
        try {
            final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getEmailFromToken(String token, String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
    
}
