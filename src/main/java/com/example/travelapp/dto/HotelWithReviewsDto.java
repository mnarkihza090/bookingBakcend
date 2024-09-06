package com.example.travelapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HotelWithReviewsDto {

    private HotelDto hotel;
    private ReviewStatsDto reviewStats;
    ///private List<ReviewDto> reviews;

    public HotelWithReviewsDto(HotelDto hotel, ReviewStatsDto reviewStats) {
        this.hotel = hotel;
        this.reviewStats = reviewStats;
    }
}
