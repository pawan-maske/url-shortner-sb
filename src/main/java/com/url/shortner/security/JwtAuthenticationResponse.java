package com.url.shortner.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Data
public class JwtAuthenticationResponse extends OncePerRequestFilter {
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
