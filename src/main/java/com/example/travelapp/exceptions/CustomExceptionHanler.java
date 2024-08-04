package com.example.travelapp.exceptions;

import com.example.travelapp.utils.ErrorDto;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHanler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request){
        ErrorDto errorDetails = new ErrorDto();
        errorDetails.setTimestamp(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setPath(request.getDescription(false));
        errorDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorDetails.setTrace(ex.getStackTrace().toString());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDto> handleValidationException(ValidationException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(), // or a custom date format
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Arrays.toString(ex.getStackTrace())
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
