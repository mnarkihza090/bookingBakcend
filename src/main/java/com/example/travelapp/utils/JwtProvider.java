package com.example.travelapp.utils;

import com.example.travelapp.dto.UserDto;
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
                .claim("firstName",userDetails.getUser().getFirstName())
                .claim("lastName",userDetails.getUser().getLastName())
                .claim("email",userDetails.getUser().getEmail())
                .claim("phoneNumber",userDetails.getUser().getPhoneNumber())
                .claim("username",userDetails.getUsername())
                .claim("userId",userDetails.getUser().getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


    public String getUsernameFromJwtToken(String token){

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
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





















