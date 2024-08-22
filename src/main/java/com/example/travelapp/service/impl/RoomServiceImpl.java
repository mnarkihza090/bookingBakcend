package com.example.travelapp.service.impl;

import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Room;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.repository.RoomRepository;
import com.example.travelapp.request.RoomSearchRequest;
import com.example.travelapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }


    @Override
    public List<Room> findRoomsByHotelId(Long hotelId) {
        Hotel hotel = hotelRepository.findHotelById(hotelId);

        if (hotel == null){
            return null;
        }

        return roomRepository.findAll(hotelId);
    }

    @Override
    public List<Room> findRoomsByCriteria(Long hotelId,RoomSearchRequest request) {

        return roomRepository.findRoomsByCriteria(hotelId,request.getCheckInDate(), request.getCheckOutDate(), request.totalGuests());
    }
}



















