package com.example.travelapp.controller;

import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.response.ApiResponse;
import com.example.travelapp.response.RoomBookingResponse;
import com.example.travelapp.service.BookingService;
import com.example.travelapp.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private DTOConverter dtoConverter;

    @PostMapping("/room/booking")
    public ResponseEntity<RoomBookingResponse> bookingRoom(
            @RequestBody RoomBookingDto request
            ){

        RoomBookingDto bookingDto = bookingService.bookRoom(request.getUserId(), request.getRoomId(),request);

        RoomBookingResponse response = dtoConverter.roomBookingResponse(bookingDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}



















