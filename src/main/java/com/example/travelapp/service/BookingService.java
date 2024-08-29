package com.example.travelapp.service;

import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.response.RoomBookingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {
    RoomBookingDto bookRoom(Long userId, Long roomId, RoomBookingDto bookingDto);
	void cancelBooking(Long bookingId);
	void calculateTotalPrice(RoomBookingDto bookingRequest);

	String getBookingReferenceNumber(Long bookingId);
	Page<RoomBookingDto> findByUserId(Pageable pageable, Long userId);
}
