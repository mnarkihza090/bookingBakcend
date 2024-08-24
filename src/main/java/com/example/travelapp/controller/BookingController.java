package com.example.travelapp.controller;

import com.example.travelapp.request.BookingRequest;
import com.example.travelapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/room/booking")
    public ResponseEntity<?> bookingRoom(
            @RequestBody BookingRequest request
            ){

        bookingService.bookRoom(request.getUserId(), request.getRoomId(), request);

        return new ResponseEntity<>("Booking room successfully", HttpStatus.CREATED);
    }
}
