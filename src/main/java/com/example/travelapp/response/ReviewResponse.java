package com.example.travelapp.response;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewResponse {

    private String comment;
    private Integer rating;
    private LocalDate publishedDate;
    private String profilePicture;
    private String username;

    public ReviewResponse() {
    }

    public ReviewResponse(String comment, Integer rating, LocalDate publishedDate, String profilePicture, String username) {
        this.comment = comment;
        this.rating = rating;
        this.publishedDate = publishedDate;
        this.profilePicture = profilePicture;
        this.username = username;
    }
}
