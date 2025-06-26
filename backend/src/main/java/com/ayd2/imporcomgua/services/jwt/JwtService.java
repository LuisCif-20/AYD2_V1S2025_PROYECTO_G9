package com.ayd2.imporcomgua.services.jwt;


public interface JwtService {

    String generateAccessToken(String email);
    String generateRefreshToken(String email);
    boolean validateAccessToken(String token);
    boolean validateRefreshToken(String token);
    String getEmailFromAccessToken(String token);
    String getEmailFromRefreshToken(String token);
    
}