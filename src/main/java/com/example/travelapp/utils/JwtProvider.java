package com.example.travelapp.utils;

import com.example.travelapp.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY = "secret";
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        String username = null;
        try {
            username = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            log.error("Error parsing");
        }
        return username;
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT signature {}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("Expired JWT token {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("Unsupported JWT token {}",e.getMessage());
        }
        return false;
    }
}





















