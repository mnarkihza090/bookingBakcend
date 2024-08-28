package com.example.travelapp.response;

import com.example.travelapp.enums.BookingStatus;
import com.example.travelapp.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RoomBookingResponse {

    private int bookingId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String state;
    private String city;
    private String country;

    private BookingStatus bookingStatus;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    private String bookingReferenceNumber;
    private PaymentType paymentType;
    private BigDecimal totalPrice;

    public RoomBookingResponse() {
    }

    public RoomBookingResponse(String firstName, String lastName, String email, String phoneNumber, String address, String state, String city, String country, LocalDate createdDate, LocalDate lastUpdatedDate, String bookingReferenceNumber, PaymentType paymentType, BigDecimal totalPrice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.state = state;
        this.city = city;
        this.country = country;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.bookingReferenceNumber = bookingReferenceNumber;
        this.paymentType = paymentType;
        this.totalPrice = totalPrice;
    }
}
