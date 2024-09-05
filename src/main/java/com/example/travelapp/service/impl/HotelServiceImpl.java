package com.example.travelapp.service.impl;

import com.example.travelapp.dto.HotelDto;
import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.dto.RoomDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Review;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.request.HotelSearchRequest;
import com.example.travelapp.service.HotelService;
import com.example.travelapp.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private DTOConverter dtoConverter;

    //@Cacheable("hotels")
    @Override
    public Page<HotelDto> getHotels(Pageable pageable) {
        Page<Hotel> hotels = hotelRepository.findAll(pageable);

        if (hotels.isEmpty()) {
            throw new RuntimeException("No hotels found in the database");
        }

        return hotels.map(hotel -> {
            double averageRating = hotel.getReviews().stream()
                    .mapToDouble(Review::getRating)
                    .average()
                    .orElse(0.0);

            HotelDto hotelDto = new HotelDto();
            hotelDto.setName(hotel.getName());
            hotelDto.setLocation(hotel.getLocation());
            hotelDto.setId(hotel.getId());
            hotelDto.setAverageRating(averageRating);
            hotelDto.setImages(hotel.getImages());
            hotelDto.setAmenities(hotel.getAmenities());
            hotelDto.setPricePerPerson(hotel.getOriginalPrice());

            List<RoomDto> roomDtos = hotel.getRooms().stream().map(room->dtoConverter.toRoomDto(room))
                            .toList();
            List<ReviewDto> reviewDtos = hotel.getReviews().stream().map(review-> dtoConverter.toReviewDto(review)).toList();

            hotelDto.setRooms(roomDtos);
            hotelDto.setReviews(reviewDtos);
            hotelDto.setRefundable(hotel.isRefundable());
            hotelDto.setOriginalPrice(hotel.getOriginalPrice());
            hotelDto.setDiscountedPrice(hotel.getDiscountedPrice());
            hotelDto.setDiscountPercentage(hotel.getDiscountPercentage());

            return hotelDto;
        });
    }

    @Override
    public HotelDto findById(Long id) {
        Hotel hotel = hotelRepository.findHotelById(id);

        if (hotel == null){
            return null;
        }
        HotelDto hotelDto = dtoConverter.toHotelDto(hotel);




        return hotelDto;
    }

    @Override
    public List<Hotel> searchHotel(HotelSearchRequest request) {
        int totalGuests = request.getAdults() + request.getChildren() + request.getInfant();

        return hotelRepository.searchHotels(request.getLocation(),request.getCheckInDate(),request.getCheckOutDate(),totalGuests);
    }
}
