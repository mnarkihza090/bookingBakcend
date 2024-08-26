package com.example.travelapp.entity;

import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal paymentAmount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "room_booking_id")
    private RoomBooking roomBooking;


    // Credit Card fields
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;

    // PayPal fields
    private String paypalEmail;
}























