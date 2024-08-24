package com.example.travelapp.controller;

import com.example.travelapp.entity.Room;
import com.example.travelapp.request.RoomSearchRequest;
import com.example.travelapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/hotels/{hotelId}/rooms")
    public ResponseEntity<?> getRoomsForHotelId(@PathVariable("hotelId") Long hotelId) {
        List<Room> rooms = roomService.findRoomsByHotelId(hotelId);

        if (rooms == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable Long roomId){
        Room room = roomService.findRoomByRoomId(roomId);

        return ResponseEntity.ok(room);
    }

    @PostMapping("/hotels/{hotelId}/rooms")
    public ResponseEntity<?> getRoomsByCriteria(
            @PathVariable Long hotelId,
            @RequestBody RoomSearchRequest request
            ){
        List<Room> rooms = roomService.findRoomsByCriteria(hotelId,request);

        if (rooms == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("available rooms not found");
        }


        return ResponseEntity.ok(rooms);
    }
}


















