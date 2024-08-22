package com.example.travelapp.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RoomSearchRequest {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalGuests;
    private int adults;
    private int children;
    private int infant;

    public int totalGuests(){
        totalGuests = adults + children + infant;
        return totalGuests;
    }

}
