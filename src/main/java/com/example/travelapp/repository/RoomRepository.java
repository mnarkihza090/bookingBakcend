package com.example.travelapp.repository;

import com.example.travelapp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{

    @Query("SELECT r FROM Room r \n" +
            "join Hotel h on r.hotel.id = h.id\n" +
            "\n" +
            "where h.id =:x")
    List<Room> findAll(@Param("x") Long hotelId);

    @Query(
            "SELECT r FROM Room r\n" +
                    "join Hotel h on h.id = r.hotel.id \n" +
                    "where h.id =:hotelId\n" +
                    "and r.availableFrom <=:checkInDate\n" +
                    "and r.availableUntil >=:checkOutDate\n" +
                    "and r.capacity >=:totalGuests\n"
    )
    List<Room> findRoomsByCriteria(@Param("hotelId") Long hotelId,
                                   @Param("checkInDate") LocalDate checkInDate,
                                   @Param("checkOutDate") LocalDate checkOutDate,
                                   @Param("totalGuests") int guestCount);

    Room findRoomById(Long roomId);
}
