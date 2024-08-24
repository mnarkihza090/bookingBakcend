package com.example.travelapp.exceptions;

public class BookingFailedException extends RuntimeException{
    public BookingFailedException(String message) {
        super(message);
    }

    public BookingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
