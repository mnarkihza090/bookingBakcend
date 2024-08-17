package com.example.travelapp.repository;

import com.example.travelapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {

    @Query("select o from Hotel o where o.id =:x")
    Hotel findHotelById(@Param("x") Long id);

}


















