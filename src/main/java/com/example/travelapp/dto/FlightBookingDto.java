package com.example.travelapp.dto;


import com.example.travelapp.entity.Flight;
import com.example.travelapp.enums.FlightClass;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import com.example.travelapp.enums.TicketType;
import com.example.travelapp.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightBookingDto {

    private Long bookingId;
    private Long userId;
    private Long flightId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime travelDate;
    private int adult;
    private int children;
    private int infant;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    // Booking Information
    private String bookingReferenceNumber;
    private String bookingStatus;
    private LocalDateTime bookingDate;
    private BigDecimal totalPrice;
    // Flight Information
    private String flightNumber;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departureAirport;
    private String arrivalAirport;
    private String airline;
    private String seatNumber;
    // Passenger Information
    private String passengerTitle;
    private String passengerFirstName;
    private String passengerLastName;
    private String passengerEmail;
    private String passengerNationality;
    private String passengerPhoneNumber;
    private String passengerPasswordExpiry;
    private String passengerPassportCountry;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime passengerDateOfBirth;
    private String passengerPassportNumber;
    // Payment Information

    private PaymentType paymentType;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;

    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    // Additional Information
    private String specialRequests;
    private String luggage;
    private String checkInStatus;

}

















