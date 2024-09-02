package com.example.travelapp.repository;

import com.example.travelapp.entity.Flight;
import com.example.travelapp.entity.FlightBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {

}
