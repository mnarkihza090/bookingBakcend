package com.example.travelapp.dto;

import com.example.travelapp.entity.FlightBooking;
import com.example.travelapp.entity.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightDto {

    private int flightId;
    private String airlineLogo;
    private String airlineName;
    private String fromCity;
    private String fromAirport;
    private String toCity;
    private String toAirport;
    private String flightNumber;
    private String airplaneType;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String flightDuration; // in hours and minutes
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private int seatAvailability;
    private boolean refundable;
    private int discountPercentage;
    private String refundPolicy;
    private String baggageInfo;
}
