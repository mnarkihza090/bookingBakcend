package com.example.travelapp.service;

import com.example.travelapp.dto.RoomDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Room;
import com.example.travelapp.request.RoomSearchRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Room findRoomByRoomId(Long roomId);
    List<Room> findRoomsByHotelId(Long hotelId);
    List<Room> findRoomsByCriteria(Long hotelId,RoomSearchRequest request);
}
