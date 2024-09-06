package com.example.travelapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewStatsDto {

    private double[] ratingPercentages;
    private int[] ratingCounts;
    private int totalReviews;
    private double averageRating;

    public ReviewStatsDto(double[] ratingPercentages, int[] ratingCounts, int totalReviews, double averageRating) {
        this.ratingPercentages = ratingPercentages;
        this.ratingCounts = ratingCounts;
        this.totalReviews = totalReviews;
        this.averageRating = averageRating;
    }
}
