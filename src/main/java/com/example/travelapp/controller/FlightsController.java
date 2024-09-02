package com.example.travelapp.controller;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.response.Pagination;
import com.example.travelapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightsController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<?> getFlight(@PathVariable("flightId") Long flightId){
        FlightDto flight = flightService.findById(flightId);

        return ResponseEntity.ok(flight);
    }

    @GetMapping("/flights")
    public ResponseEntity<?> getFlights(
            @RequestParam(defaultValue = "0",name = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = "6",name = "pageSize") int pageSize){

        Page<FlightDto> flights = flightService.findAll(PageRequest.of(pageNumber, pageSize));

        return ResponseEntity.ok(
                new Pagination<List<FlightDto>>(
                        flights.getTotalPages(),
                        flights.getNumber(),
                        flights.getSize(),
                        flights.getContent(),
                        flights.getTotalElements())
        );
    }
}
















