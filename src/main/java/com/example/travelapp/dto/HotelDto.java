package com.example.travelapp.dto;

import com.example.travelapp.entity.Amenity;
import com.example.travelapp.entity.Review;
import com.example.travelapp.entity.Room;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class HotelDto {
    private Long id;
    private String name;
    private String location;
    private Double averageRating;
    private List<ReviewDto> reviews;
    private Double pricePerPerson;
    private String country;
    private BigDecimal childPrice;
    private BigDecimal infantPrice;
    private List<RoomDto> rooms;
    private List<String> images;
    private List<Amenity> amenities;

    private int discountPercentage;
    private boolean refundable;
    private Double originalPrice;
    private Double discountedPrice;

    public HotelDto() {
    }

    public HotelDto(Long id, String name, String location, Double averageRating, List<ReviewDto> reviews) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.averageRating = averageRating;
        this.reviews = reviews;
    }
}
