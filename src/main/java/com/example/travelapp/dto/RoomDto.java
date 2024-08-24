package com.example.travelapp.dto;

import com.example.travelapp.entity.Amenity;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RoomDto {
    private Long id;
    private String roomNumber;
    private Double pricePerNight;
    private String roomType;
    private String description;
    private int capacity;
    private int standardOccupancy;
    private BigDecimal extraAdultPrice;
    private BigDecimal extraChildPrice;
    private BigDecimal extraInfantPrice;
    private List<String> images;
    private String location;

    private Long hotelId;
    private List<Amenity> amenities;
    private RoomStatus roomStatus = RoomStatus.AVAILABLE;
    private LocalDate availableFrom;
    private LocalDate availableUntil;

}
