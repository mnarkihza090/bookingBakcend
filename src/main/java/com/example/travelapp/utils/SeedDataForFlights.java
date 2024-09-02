package com.example.travelapp.utils;

import com.example.travelapp.entity.Flight;
import com.example.travelapp.enums.FlightStatus;
import com.example.travelapp.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                new Flight("Turkish Airline", "AirlineA_Logo.png", "Istanbul", "Istanbul Sabiha Gökçen Airport", "London", "Londra Heathrow Airport", "TK1984", "Boeing 777",
                        LocalDateTime.of(2024, 9, 1, 10, 10), LocalDateTime.of(2024, 9, 1, 14, 10), "4h 00m",
                        new BigDecimal("500.00"), new BigDecimal("450.00"), 10, "Non-refundable", "1 baggage 23kg",100,true),
                new Flight("Fly Emirates Airline", "AirlineB_Logo.png", "New York", "John F. Kennedy International Airport", "Paris", "Paris-charles De Gaulle Airport", "AF007", "Airbus A380",
                        LocalDateTime.of(2024, 9, 5, 8, 0),
                        LocalDateTime.of(2024, 9, 5, 11, 30), "3h 30m",
                        new BigDecimal("700.00"), new BigDecimal("630.00"), 10, "Refundable with conditions", "2 baggage 23kg",150,true),
                new Flight("Qatar Airways", "AirlineC_Logo.png", "Tokyo", "Narita International Airport", "Sydney", "Sydney Airport", "QF22", "Boeing 787",
                        LocalDateTime.of(2024, 11, 11, 21, 50), LocalDateTime.of(2024, 11, 11, 23, 15), "2h 05m",
                        new BigDecimal("800.00"), new BigDecimal("720.00"), 10, "Non-refundable", "1 baggage 23kg",79,false),
                new Flight("Emirates Airline", "AirlineD_Logo.png", "Dubai", "Dubai International Airport", "Los Angeles", "Los Angeles International Airport", "EK215", "Airbus A380",
                        LocalDateTime.of(2024, 9, 15, 2, 30), LocalDateTime.of(2024, 9, 15, 4, 50), "2h 20m",
                        new BigDecimal("1200.00"), new BigDecimal("1080.00"), 10, "Refundable with conditions", "2 baggage 32kg",80,false),
                new Flight("Swiss Airline", "AirlineE_Logo.png", "Berlin", "Berlin Tegel Airport", "Moscow", "Şeremetyevo International Airport", "SU232", "Sukhoi Superjet 100",
                        LocalDateTime.of(2024, 9, 20, 7, 15), LocalDateTime.of(2024, 9, 20, 11, 15), "4h 00m",
                        new BigDecimal("350.00"), new BigDecimal("315.00"), 10, "Non-refundable", "1 baggage 20kg",120,true),
                new Flight("Anadolu Jet Airline", "AirlineF_Logo.png", "Rome", "Fiumicino – Leonardo da Vinci Airport", "Istanbul", "Istanbul Atatürk Airport", "TK1862", "Boeing 737",
                        LocalDateTime.of(2024, 9, 25, 13, 10), LocalDateTime.of(2024, 9, 25, 15, 55), "2h 45m",
                        new BigDecimal("400.00"), new BigDecimal("360.00"), 10, "Refundable with conditions", "1 baggage 23kg",79,true),
                new Flight("Air France Airline", "AirlineG_Logo.png", "Madrid", "Madrid International Airport", "Lisbon", "Portela Airport", "IB3102", "Airbus A320",
                        LocalDateTime.of(2024, 9, 30, 9, 0), LocalDateTime.of(2024, 9, 30, 10, 20), "1h 20m",
                        new BigDecimal("150.00"), new BigDecimal("135.00"), 10, "Non-refundable", "1 baggage 15kg",100,false),
                new Flight("American Airlines", "AirlineH_Logo.png", "Beijing", "Pekin Daxing International Airport", "Singapore", "Singapore Airport", "SQ807", "Boeing 777",
                        LocalDateTime.of(2024, 10, 5, 20, 15), LocalDateTime.of(2024, 10, 6, 22, 45), "2h 30m",
                        new BigDecimal("650.00"), new BigDecimal("585.00"), 10, "Refundable with conditions", "2 baggage 23kg",100,true),
                new Flight("Air Canada Airline", "AirlineI_Logo.png", "Sydney", "Sydney Airport", "Auckland", "Auckland Airport", "NZ104", "Airbus A320",
                        LocalDateTime.of(2024, 10, 10, 14, 20), LocalDateTime.of(2024, 10, 10, 19, 30), "5h 10m",
                        new BigDecimal("200.00"), new BigDecimal("180.00"), 10, "Non-refundable", "1 baggage 20kg",125,true),
                new Flight("Pegasus Airline", "AirlineJ_Logo.png", "Toronto", "Toronto Pearson International Airport", "Vancouver", "Vancouver International Airport", "AC033", "Boeing 777",
                        LocalDateTime.of(2024, 10, 15, 18, 0), LocalDateTime.of(2024, 10, 15, 20, 20), "2h 20m",
                        new BigDecimal("500.00"), new BigDecimal("450.00"), 10, "Refundable with conditions", "2 baggage 23kg",100,false)
        );

        flightRepository.saveAll(flights);

    }
}
