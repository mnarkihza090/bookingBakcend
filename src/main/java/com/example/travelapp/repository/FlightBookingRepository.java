package com.example.travelapp.repository;

import com.example.travelapp.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
}
