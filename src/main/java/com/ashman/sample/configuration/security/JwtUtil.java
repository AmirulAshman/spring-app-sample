package com.ashman.sample.configuration.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String SECRET = "my_super_secret_key_1234567890123456";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final int EXPIRATION_LENGTH = 1000 * 60 * 60 * 10;

    
    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser() //
                .verifyWith(SECRET_KEY) //
                .decryptWith(SECRET_KEY) //
                .build() //
                .parseSignedClaims(token) //
                .getPayload();
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder() //
                .claims(claims) //
                .subject(subject) //
                .issuedAt(new Date(System.currentTimeMillis())) //
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_LENGTH)) //
                .signWith(SECRET_KEY, Jwts.SIG.HS256) //
                .compact();
    }

}
