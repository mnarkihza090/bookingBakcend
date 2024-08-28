package com.example.travelapp.service;

import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.response.RoomBookingResponse;

public interface BookingService {
    RoomBookingDto bookRoom(Long userId, Long roomId, RoomBookingDto bookingDto);
	void cancelBooking(Long bookingId);
	void calculateTotalPrice(RoomBookingDto bookingRequest);

	String getBookingReferenceNumber(Long bookingId);
}
