package com.ayd2.imporcomgua.services.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.auth.AuthResponseDTO;
import com.ayd2.imporcomgua.dto.auth.LoginRequestDTO;
import com.ayd2.imporcomgua.exceptions.ServiceException;
import com.ayd2.imporcomgua.services.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    @Override
    public Map<String, String> login(LoginRequestDTO loginRequestDTO)
            throws AuthenticationException, ServiceException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(), 
                        loginRequestDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtService.generateAccessToken(authentication.getName());
        String refreshToken = jwtService.generateRefreshToken(authentication.getName());
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    @Override
    public AuthResponseDTO refreshToken(String refreshToken) throws ServiceException {
        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw new ServiceException("Token de refresco invalido");
        }
        String username = jwtService.getEmailFromRefreshToken(refreshToken);
        String newAccessToken = jwtService.generateAccessToken(username);
        return new AuthResponseDTO(newAccessToken);
    }
    
}
