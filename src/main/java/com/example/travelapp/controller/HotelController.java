package com.example.travelapp.controller;

import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.response.Pagination;
import com.example.travelapp.service.HotelService;
import com.example.travelapp.service.ReviewService;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.DTOConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HotelController {

    private static final Logger log = LoggerFactory.getLogger(HotelController.class);
    @Autowired
    public HotelService hotelService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping("/hotels")
    public ResponseEntity<?> getHotels(
            @RequestParam(defaultValue = "0",name = "pageNumber") String pageNumber,
            @RequestParam(defaultValue = "6",name = "pageSize") String pageSize
    ){

        int pageN = Integer.parseInt(pageNumber);
        int pageS = Integer.parseInt(pageSize);

        Pageable pageable = PageRequest.of(pageN, pageS);
        Page<Hotel> hotels = hotelService.getHotels(pageable);

        return ResponseEntity.ok(
                new Pagination<List<Hotel>>(
                        hotels.getTotalPages(),
                        hotels.getNumber(),
                        hotels.getSize(),
                        hotels.getContent(),
                        hotels.getTotalElements()));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable("hotelId") Long hotelId){
        Hotel hotel = hotelService.findById(hotelId);

        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @PostMapping("/hotels/{hotelId}/addReview")
    public ResponseEntity<?> addReviewToHotel(@PathVariable("hotelId") Long hotelId,
                                              @RequestBody ReviewDto reviewDto,
                                              HttpServletRequest request
            ){

            Hotel hotel = hotelService.findById(hotelId);
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
            String currentUser = authentication.getName();
            UserDto userDto = userService.findByEmail(currentUser);

            log.info("UserDto: " + userDto);
            log.info("Authentication: " + authentication);
            log.info("Current User: " + currentUser);

        Map<String, Object> response = new HashMap<>();

        if (userDto != null && hotel != null){
            ReviewDto review = new ReviewDto();

            review.setUserId(userDto.getId());
            review.setHotelId(hotel.getId());
            review.setRating(reviewDto.getRating());
            review.setComment(reviewDto.getComment());
            review.setPublishedDate(reviewDto.getPublishedDate());

            reviewService.addReview(review);

            response.put("status", "success");
            response.put("message", "Review added successfully");

            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Review could not be added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/hotels/{hotelId}/reviews")
    public ResponseEntity<?> getReviewsForHotel(@PathVariable("hotelId") Long hotelId){

        List<ReviewDto> reviews = reviewService.getReviewsByHotelId(hotelId);

        if(reviews == null){
            return new ResponseEntity<>("No reviews found for this hotel", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(reviews);
    }
}
