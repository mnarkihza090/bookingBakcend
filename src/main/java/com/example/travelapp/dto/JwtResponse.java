package com.example.travelapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private boolean isEnabled;
    private String phoneNumber;
    private String email;
    private List<String> roles;
    private String profilePictureUrl;

    public JwtResponse(String token) {
        this.token = token;
    }


    public JwtResponse(String username, String token, String email, String profilePictureUrl,boolean isEnabled,String firstName,String lastName,String password,String phoneNumber) {
        this.username = username;
        this.token = token;
        this.isEnabled = isEnabled;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

}
