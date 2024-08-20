package com.example.travelapp.entity;

import com.example.travelapp.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private int capacity;
    private Double pricePerNight;
    private String roomType;
    private String description;

    @ElementCollection
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @ElementCollection
    private List<Amenity> amenities;

    private RoomStatus roomStatus;

    private LocalDate availableFrom;
    private LocalDate availableUntil;

    //@OneToMany(mappedBy = "room")
    //private List<Booking> bookings; // Bu oda için yapılan rezervasyonlar

    public Room() {
    }

    public Room(String roomNumber, int capacity, Double pricePerNight, String roomType, String description,
                List<String> images,List<Amenity> amenities,
                LocalDate availableFrom, LocalDate availableUntil) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.description = description;
        this.images = images;
        this.amenities = amenities;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
    }
}
