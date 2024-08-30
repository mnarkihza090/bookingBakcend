package com.example.travelapp.controller;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;
import com.example.travelapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightsController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<?> getFlights(){
        List<FlightDto> flights = flightService.findAll();

        return ResponseEntity.ok(flights);
    }
}














