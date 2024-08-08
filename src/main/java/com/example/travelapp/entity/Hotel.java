package com.example.travelapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private int roomNumber;
    private String location;
    private String description;
    private Double pricePerPerson;
    @ElementCollection
    private List<String> images;
    private String rating;
    @ElementCollection
    private List<String> amenities;
    @OneToMany(mappedBy = "hotel")
    private List<Review> reviews;

    public Hotel() {
    }

    public Hotel(String name, String country, int roomNumber, String location, String description, Double pricePerPerson, List<String> images, String rating, List<String> amenities) {
        this.name = name;
        this.country = country;
        this.roomNumber = roomNumber;
        this.location = location;
        this.description = description;
        this.pricePerPerson = pricePerPerson;
        this.images = images;
        this.rating = rating;
        this.amenities = amenities;
    }
}

























