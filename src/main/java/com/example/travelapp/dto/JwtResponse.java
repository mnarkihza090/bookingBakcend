package com.example.travelapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private Long id;
    private String email;
    private List<String> roles;
    private String profilePictureUrl;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(String username,String token, String email, String profilePictureUrl) {
        this.username = username;
        this.token = token;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
    }

    public JwtResponse(String token, String email, List<String> roles, String profilePictureUrl) {
        this.token = token;
        this.email = email;
        this.roles = roles;
        this.profilePictureUrl = profilePictureUrl;
    }

    public JwtResponse(String accessToken, Long id, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}
