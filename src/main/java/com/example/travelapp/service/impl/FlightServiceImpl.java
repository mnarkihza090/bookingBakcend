package com.example.travelapp.service.impl;

import com.example.travelapp.entity.Flight;
import com.example.travelapp.repository.FlightRepository;
import com.example.travelapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
}
