package com.example.travelapp.service.impl;

import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.entity.Flight;
import com.example.travelapp.repository.FlightRepository;
import com.example.travelapp.service.FlightService;
import com.example.travelapp.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public List<FlightDto> findAll() {

        List<Flight> flights = flightRepository.findAll();

        List<FlightDto> flightDtos = flights.stream()
                .map(flight -> dtoConverter.toFlightDto(flight))
                .toList();

        return flightDtos;
    }

    @Override
    public FlightDto findById(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);

        FlightDto flightDto = dtoConverter.toFlightDto(flight.get());

        return flightDto;
    }
}
