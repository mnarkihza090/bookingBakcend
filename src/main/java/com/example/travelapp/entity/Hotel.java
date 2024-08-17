package com.example.travelapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(min = 10,max = 1000)
    private String description;
    private Double pricePerPerson;
    @ElementCollection
    private List<String> images;
    @ElementCollection
    private List<Amenity> amenities;
    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private List<Review> reviews;

    public Hotel() {
    }

    public Hotel(String name, String country, int roomNumber,
                 String location, String description, Double pricePerPerson, List<String> images, List<Amenity> amenities) {
        this.name = name;
        this.country = country;
        this.roomNumber = roomNumber;
        this.location = location;
        this.description = description;
        this.pricePerPerson = pricePerPerson;
        this.images = images;
        this.amenities = amenities;
    }
}

























