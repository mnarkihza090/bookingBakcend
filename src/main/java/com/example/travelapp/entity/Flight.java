package com.example.travelapp.entity;

import com.example.travelapp.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    private int seatAvailability;
    private boolean refundable;

    private String flightDuration; // in hours and minutes
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private int discountPercentage;
    private String refundPolicy;
    private String baggageInfo;

    public Flight() {
    }

    @OneToMany(mappedBy = "flight")
    private List<FlightBooking> flightBookings;

    @ManyToMany(mappedBy = "flights")
    private List<User> users;


    public Flight(String airlineName,String airlineLogo, String fromCity, String fromAirport, String toCity, String toAirport,
                  String flightNumber, String airplaneType, LocalDateTime departureTime,
                  LocalDateTime arrivalTime, String flightDuration, BigDecimal originalPrice,
                  BigDecimal discountedPrice, int discountPercentage, String refundPolicy,
                  String baggageInfo,int seatAvailability,boolean refundable) {
        this.airlineName = airlineName;
        this.airlineLogo = airlineLogo;
        this.fromCity = fromCity;
        this.fromAirport = fromAirport;
        this.toCity = toCity;
        this.toAirport = toAirport;
        this.flightNumber = flightNumber;
        this.airplaneType = airplaneType;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.discountPercentage = discountPercentage;
        this.refundPolicy = refundPolicy;
        this.baggageInfo = baggageInfo;
        this.seatAvailability = seatAvailability;
        this.refundable = refundable;
    }
}






















