package com.example.travelapp.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Amenity {
    private String name;
    private String iconUrl;

    public Amenity() {
    }

    public Amenity(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }
}
