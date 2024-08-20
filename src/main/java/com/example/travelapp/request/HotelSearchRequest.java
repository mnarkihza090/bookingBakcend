package com.example.travelapp.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    private String location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adults;
    private int children;
    private int infant;
}
