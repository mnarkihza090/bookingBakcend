package com.example.travelapp.request;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Address;
import com.example.travelapp.entity.Payment;
import com.example.travelapp.entity.Room;
import com.example.travelapp.entity.User;
import com.example.travelapp.enums.BookingStatus;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {

    private int bookingId;
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
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;

    private Payment payment;

}
