package com.example.travelapp.repository;

import com.example.travelapp.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    Page<Hotel> findAll(Pageable pageable);
    @Query("select o from Hotel o where o.id =:x")
    Hotel findHotelById(@Param("x") Long id);

    @Query("SELECT h FROM Hotel h WHERE h.location = :location AND h.roomNumber >= :totalGuests")
    List<Hotel> findHotelsByCriteria(@Param("location") String location, @Param("totalGuests") int totalGuests);


    @Query("SELECT h \n" +
            "FROM Hotel h\n" +
            "JOIN Room r ON r.hotel.id = h.id\n" +
            "WHERE h.location = :location\n" +
            "AND " +
            "r.availableFrom <=:journeyDate " +
            "AND r.availableUntil >=:returnDate\n" +
            "and r.capacity >=:totalGuests\n")
    List<Hotel> searchHotels(@Param("location") String location,
                             @Param("journeyDate") LocalDate journeyDate,
                             @Param("returnDate") LocalDate returnDate,
                             @Param("totalGuests") int totalGuest);
}


















