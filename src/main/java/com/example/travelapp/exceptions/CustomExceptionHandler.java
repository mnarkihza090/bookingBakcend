package com.example.travelapp.exceptions;

import com.example.travelapp.utils.ErrorDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> handleEmailNotFoundException(EmailNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(BookingFailedException.class)
    public ResponseEntity<?> handleBookingFailedException(BookingFailedException exception) {


        if (exception.getStackTrace().length > 0){
            StackTraceElement element = exception.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}, fileName: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber(),element.getFileName());
        }
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorDto.setPath(request.getDescription(false));
        errorDto.setMessage(ex.getMessage());
        errorDto.setTrace(ex.getStackTrace().toString());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleResourceNotFoundException(NullPointerException exception) {


        if (exception.getStackTrace().length > 0){
            StackTraceElement element = exception.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleResourceNotFoundException(HttpMessageNotReadableException exception) {


        if (exception.getStackTrace().length > 0){
            StackTraceElement element = exception.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleResourceNotFoundException(MethodArgumentTypeMismatchException exception) {


        if (exception.getStackTrace().length > 0){
            StackTraceElement element = exception.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
