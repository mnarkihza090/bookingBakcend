package com.example.travelapp.controller;

import com.example.travelapp.dto.FlightBookingDto;
import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.response.ApiResponse;
import com.example.travelapp.response.RoomBookingResponse;
import com.example.travelapp.service.BookingService;
import com.example.travelapp.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    @PostMapping("/flight/booking")
    public ResponseEntity<?> bookFlight(@RequestBody FlightBookingDto flightBookingDto){

        FlightBookingDto flightBookingDto1 =
                bookingService.bookFlight(flightBookingDto.getUserId(),flightBookingDto.getFlightId(),flightBookingDto);

        //return ResponseEntity.status(HttpStatus.OK).body(flightBookingDto1);
        return ResponseEntity.ok(flightBookingDto1);
    }

    @GetMapping("/flight/booking")
    public ResponseEntity<?> getFlightBookingsFormUser(@RequestParam Long userId){
        List<FlightBookingDto> flightBookingDtos = bookingService.findFlightBookingsByUserId(userId);

        return ResponseEntity.ok(flightBookingDtos);
    }
}



















