package com.ayd2.imporcomgua.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String json = """
                {
                    "status": 401,
                    "title": "Authentication Error",
                    "detail": "%s",
                    "error_category": "Auth",
                    "timestamp": "%s"
                }
                """.formatted(
                authException.getMessage() != null ? authException.getMessage()
                        : "Necesitas autenticarte para acceder a este recurso",
                java.time.Instant.now().toString());

        response.getWriter().write(json);
    }

}
