package com.ayd2.imporcomgua.services.auth;

import java.util.Map;

import org.springframework.security.core.AuthenticationException;

import com.ayd2.imporcomgua.dto.auth.AuthResponseDTO;
import com.ayd2.imporcomgua.dto.auth.LoginRequestDTO;
import com.ayd2.imporcomgua.exceptions.ServiceException;

public interface AuthService {
    
    Map<String, String> login(LoginRequestDTO loginRequestDTO) throws AuthenticationException, ServiceException;
    AuthResponseDTO refreshToken(String refreshToken) throws ServiceException;

}
