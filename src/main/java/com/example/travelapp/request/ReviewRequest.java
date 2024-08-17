package com.example.travelapp.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequest {
    private String comment;
    private int rating;
}
