package com.example.travelapp.repository;

import com.example.travelapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query("SELECT p FROM Payment p \n" +
            "join RoomBooking r on r.bookingId= p.roomBooking.bookingId\n" +
            "where p.roomBooking.bookingId =:x")
    Payment findPaymentBookingId(@Param("x") Long id);
}
