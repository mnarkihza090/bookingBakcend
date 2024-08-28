package com.example.travelapp.repository;

import com.example.travelapp.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking,Long> {
    @Query("SELECT r FROM RoomBooking r where r.bookingId =:x")
    RoomBooking findBookingById(@Param("x") Long id);


}
