package com.example.travelapp.service;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {
    Page<FlightDto> findAll(Pageable pageable);
    FlightDto findById(Long flightId);
}
