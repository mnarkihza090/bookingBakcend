package com.example.travelapp.dto;

import com.example.travelapp.enums.BookingStatus;
import com.example.travelapp.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RoomBookingDto {

    private Long userId;
    private Long roomId;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String state;
    private String city;
    private String country;

    private int children;
    private int adults;
    private int infant;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String note;

    private String bookingReferenceNumber;
    private BookingStatus bookingStatus;

    private PaymentType paymentType;
    // Credit Card fields
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;

    // PayPal fields
    private String paypalEmail;

    private BigDecimal totalPrice;
}
