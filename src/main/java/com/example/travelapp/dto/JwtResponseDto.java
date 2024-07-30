package com.example.travelapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponseDto {

    private String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
