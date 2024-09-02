package com.example.travelapp.entity;

import com.example.travelapp.enums.*;
import com.example.travelapp.service.PricingService;
import com.example.travelapp.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private LocalDateTime travelDate;
    private int adult;
    private int children;
    private int infant;
    // Booking Information
    private String bookingReferenceNumber;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

   @Enumerated(EnumType.STRING)
    private TicketType ticketType;

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
    private LocalDateTime passengerDateOfBirth;
    private String passengerPassportNumber;
    private String passengerPassportCountry;
    private String passportExpiry;

    // Payment Information
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    // Additional Information
    private String specialRequests;
    private String luggage;
    private BigDecimal totalPrice;



}
