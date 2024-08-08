package com.example.travelapp.service;

import com.example.travelapp.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotels();
    Hotel findById(Long id);
}
