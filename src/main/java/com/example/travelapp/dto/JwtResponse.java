package com.example.travelapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }



}
