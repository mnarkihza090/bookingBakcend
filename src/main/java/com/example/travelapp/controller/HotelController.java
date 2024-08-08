package com.example.travelapp.controller;

import com.example.travelapp.entity.Hotel;
import com.example.travelapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    public HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> hotels = hotelService.getHotels();

        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable("hotelId") Long hotelId){
        return new ResponseEntity<>(hotelService.findById(hotelId),HttpStatus.OK);
    }
}
