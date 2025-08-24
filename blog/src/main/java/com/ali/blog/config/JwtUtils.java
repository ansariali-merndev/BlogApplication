package com.ali.blog.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Configuration
public class JwtUtils {

    private SecretKey getSecretkey () {
        String secretKey = "S9WKx8WANG@GABVdEVskjcbehjBJCVBRGNBCVJVBJBFXMNKJSJDKLASJDLSHDFKKCBDMVB";
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken (String username, UUID id) {
        long validateInMs = 24 * 60 * 60 * 1000;
        return Jwts
                .builder()
                .subject(username)
                .claim("id", id)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + validateInMs))
                .signWith(getSecretkey())
                .compact();
    }

    public String getUsernameFromToken (String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretkey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid (String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Date date =  claims.getExpiration();
        return date != null && date.after(new Date());
    }

}
