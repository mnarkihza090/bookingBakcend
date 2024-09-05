package com.example.travelapp.service;

import com.example.travelapp.dto.FlightBookingDto;
import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.entity.FlightBooking;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.response.RoomBookingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingService {
    RoomBookingDto bookRoom(Long userId, Long roomId, RoomBookingDto bookingDto);
	void cancelBooking(Long bookingId);
	void calculateTotalPrice(RoomBookingDto bookingRequest);

	String getBookingReferenceNumber(Long bookingId);
	Page<RoomBookingDto> findByUserId(Pageable pageable, Long userId);
	FlightBookingDto bookFlight(Long userId, Long flightId,FlightBookingDto bookingDto);

	List<FlightBookingDto> findFlightBookingsByUserId(Long userId);
}
