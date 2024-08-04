package com.example.travelapp.utils;

import com.example.travelapp.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public JwtProvider(@Value("${jwt.secret}") String secretKey,@Value("${jwt.expiration}") long expirationTime) {
        SECRET_KEY = secretKey;
        EXPIRATION_TIME = expirationTime;
    }


    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("profilePictureUrl",userDetails.getUser().getProfilePicture())
                .claim("username",userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
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





















