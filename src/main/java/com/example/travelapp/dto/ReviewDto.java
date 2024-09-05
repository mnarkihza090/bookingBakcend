package com.example.travelapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDto {

    private Long id;
    private Long hotelId;
    private Long userId;
    private String profilePicture;
    private String username;
    private int rating;
    private String comment;
    private LocalDateTime publishedDate;

    public ReviewDto() {
    }

    public ReviewDto(Long id) {
        this.id = id;
    }
}
