package com.example.travelapp.repository;

import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByHotel(Hotel hotel);
    @Query("SELECT r FROM Review r WHERE r.hotel.id = :x")
    List<Review> findReviewsByHotelId(@Param("x") Long hotelId);
}
