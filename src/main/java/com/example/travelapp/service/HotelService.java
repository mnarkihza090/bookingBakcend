package com.example.travelapp.service;

import com.example.travelapp.entity.Hotel;
import com.example.travelapp.request.HotelSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface HotelService {
    Page<Hotel> getHotels(Pageable pageable);
    Hotel findById(Long id);
    List<Hotel> searchHotel(HotelSearchRequest request);
}
