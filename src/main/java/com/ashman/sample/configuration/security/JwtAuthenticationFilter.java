package com.ashman.sample.configuration.security;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("Request URI: " + request.getRequestURI());
        String jwt = getJwtFromRequest(request);
        if (StringUtils.isNotBlank(jwt) && validateToken(jwt)) {
            // Extract user details from the token if needed
            String username = getUsernameFromJWT(jwt);

            // Set the authentication in the SecurityContext
            var authentication = new UsernamePasswordAuthenticationToken(username, null, List.of() // Set roles if
                                                                                                   // necessary
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        return JwtUtil.validateToken(token, "user");
    }

    private String getUsernameFromJWT(String token) {
        // Extract username from token
        return "user"; // Replace with real extraction logic
    }

}
