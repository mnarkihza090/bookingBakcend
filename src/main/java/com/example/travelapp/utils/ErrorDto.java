package com.example.travelapp.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ErrorDto {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String path;
    private String error;
    private String trace;

    public ErrorDto() {
    }

    public ErrorDto(String message, int statusCode, LocalDateTime timestamp, String path, String error, String trace) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.path = path;
        this.error = error;
        this.trace = trace;
    }
}