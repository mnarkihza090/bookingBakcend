package com.example.travelapp.service;

import com.example.travelapp.dto.HotelDto;
import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDto> getReviewsByHotelId(Long hotelId);
    void addReview(ReviewDto reviewDto);
    Map<Integer,Long> getRatingCountsByHotelId(HotelDto hotelDto);
    double getAverageRating(Long hotelId);
}
