package com.example.travelapp.utils;

import com.example.travelapp.entity.Flight;
import com.example.travelapp.enums.FlightStatus;
import com.example.travelapp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//@Component
public class SeedDataForFlights implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Flight> flights = Arrays.asList(
                new Flight("Turkish Airline","AirlineA_Logo.png", "Istanbul", "IST", "London", "LHR", "TK1984", "Boeing 777",
                        LocalDateTime.of(2024, 9, 1, 14, 30), LocalDateTime.of(2024, 9, 1, 16, 45), "2h 15m",
                        new BigDecimal("500.00"), new BigDecimal("450.00"), 10, "Non-refundable", "1 baggage 23kg"),
                new Flight("Fly Emirates Airline","AirlineB_Logo.png", "New York", "JFK", "Paris", "CDG", "AF007", "Airbus A380",
                        LocalDateTime.of(2024, 9, 5, 8, 0), LocalDateTime.of(2024, 9, 5, 16, 30), "8h 30m",
                        new BigDecimal("700.00"), new BigDecimal("630.00"), 10, "Refundable with conditions", "2 baggage 23kg"),
                new Flight("Qatar Airways","AirlineD_Logo.png", "Tokyo", "NRT", "Sydney", "SYD", "QF22", "Boeing 787",
                        LocalDateTime.of(2024, 9, 10, 22, 50), LocalDateTime.of(2024, 9, 11, 10, 15), "9h 25m",
                        new BigDecimal("800.00"), new BigDecimal("720.00"), 10, "Non-refundable", "1 baggage 23kg"),
                new Flight("Emirates Airline","AirlineD_Logo.png", "Dubai", "DXB", "Los Angeles", "LAX", "EK215", "Airbus A380",
                        LocalDateTime.of(2024, 9, 15, 2, 30), LocalDateTime.of(2024, 9, 15, 12, 50), "16h 20m",
                        new BigDecimal("1200.00"), new BigDecimal("1080.00"), 10, "Refundable with conditions", "2 baggage 32kg"),
                new Flight("Swiss Airline","AirlineE_Logo.png", "Berlin", "TXL", "Moscow", "SVO", "SU232", "Sukhoi Superjet 100",
                        LocalDateTime.of(2024, 9, 20, 7, 15), LocalDateTime.of(2024, 9, 20, 11, 5), "2h 50m",
                        new BigDecimal("350.00"), new BigDecimal("315.00"), 10, "Non-refundable", "1 baggage 20kg"),
                new Flight("Anadolu Jet Airline","AirlineF_Logo.png", "Rome", "FCO", "Istanbul", "IST", "TK1862", "Boeing 737",
                        LocalDateTime.of(2024, 9, 25, 13, 10), LocalDateTime.of(2024, 9, 25, 15, 55), "2h 45m",
                        new BigDecimal("400.00"), new BigDecimal("360.00"), 10, "Refundable with conditions", "1 baggage 23kg"),
                new Flight("Air France Airline","AirlineG_Logo.png", "Madrid", "MAD", "Lisbon", "LIS", "IB3102", "Airbus A320",
                        LocalDateTime.of(2024, 9, 30, 9, 0), LocalDateTime.of(2024, 9, 30, 10, 20), "1h 20m",
                        new BigDecimal("150.00"), new BigDecimal("135.00"), 10, "Non-refundable", "1 baggage 15kg"),
                new Flight("American Airlines","AirlineH_Logo.png", "Beijing", "PEK", "Singapore", "SIN", "SQ807", "Boeing 777",
                        LocalDateTime.of(2024, 10, 5, 23, 15), LocalDateTime.of(2024, 10, 6, 5, 45), "6h 30m",
                        new BigDecimal("650.00"), new BigDecimal("585.00"), 10, "Refundable with conditions", "2 baggage 23kg"),
                new Flight("Air Canada Airline","AirlineI_Logo.png", "Sydney", "SYD", "Auckland", "AKL", "NZ104", "Airbus A320",
                        LocalDateTime.of(2024, 10, 10, 14, 20), LocalDateTime.of(2024, 10, 10, 19, 30), "3h 10m",
                        new BigDecimal("200.00"), new BigDecimal("180.00"), 10, "Non-refundable", "1 baggage 20kg"),
                new Flight("Pegasus Airline","AirlineJ_Logo.png", "Toronto", "YYZ", "Vancouver", "YVR", "AC033", "Boeing 777",
                        LocalDateTime.of(2024, 10, 15, 18, 0), LocalDateTime.of(2024, 10, 15, 20, 20), "5h 20m",
                        new BigDecimal("500.00"), new BigDecimal("450.00"), 10, "Refundable with conditions", "2 baggage 23kg")
        );

        flightRepository.saveAll(flights);

    }
}
