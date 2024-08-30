package com.example.travelapp.service;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;

import java.util.List;

public interface FlightService {
    List<FlightDto> findAll();
    FlightDto findById(Long flightId);
}
