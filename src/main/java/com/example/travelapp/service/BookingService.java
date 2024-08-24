package com.example.travelapp.service;

import com.example.travelapp.dto.RoomDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.request.BookingRequest;

public interface BookingService {
    void bookRoom(Long userId, Long roomId,BookingRequest bookingRequest);
	void cancelBooking(Long bookingId);
	void calculateTotalPrice(BookingRequest bookingRequest);
}
