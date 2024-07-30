package com.example.travelapp.dto;

import lombok.Data;

@Data
public class ErrorDto {

    private String message;
    private int statusCode;

}
