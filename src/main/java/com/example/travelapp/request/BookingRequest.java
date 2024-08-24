package com.example.travelapp.request;

import com.example.travelapp.entity.Payment;
import com.example.travelapp.entity.Room;
import com.example.travelapp.entity.User;
import com.example.travelapp.enums.BookingStatus;
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
    private int children;
    private int adults;
    private int infant;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private BookingStatus bookingStatus;
    private String note;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    private String bookingReferenceNumber;
    private Payment payment;


}
