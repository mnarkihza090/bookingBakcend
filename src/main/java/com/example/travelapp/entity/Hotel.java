package com.example.travelapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private int roomNumber;
    private String location;
    @Size(min = 10,max = 1000)
    private String description;

    private boolean refundable;
    private Double originalPrice;
    private Double discountedPrice;
    private int discountPercentage;

    @OneToMany(mappedBy = "hotel",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Room> rooms;

    @ElementCollection
    private List<String> images;
    @ElementCollection
    private List<Amenity> amenities;

    @OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<Review> reviews;

    public Hotel() {
    }

    public Hotel(String name, String country, int roomNumber,
                 String location, String description, Double originalPrice,
                 List<String> images, List<Amenity> amenities,List<Room> rooms) {
        this.name = name;
        this.country = country;
        this.roomNumber = roomNumber;
        this.location = location;
        this.description = description;
        this.originalPrice = originalPrice;
        this.images = images;
        this.amenities = amenities;
        this.rooms = rooms;
        this.refundable = refundable;
    }


}

























