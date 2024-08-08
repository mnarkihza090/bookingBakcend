package com.example.travelapp.service.impl;

import com.example.travelapp.entity.Hotel;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getHotels() {
        List<Hotel> hotels = hotelRepository.findAll();

        if (hotels.isEmpty()){
            throw new RuntimeException("No hotels found in the database");
        }
        return hotels;
    }

    @Override
    public Hotel findById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);

        if (hotel.isEmpty()){
            throw new RuntimeException("Hotel not found with id: " + id);
        }

        return hotel.get();
    }
}
