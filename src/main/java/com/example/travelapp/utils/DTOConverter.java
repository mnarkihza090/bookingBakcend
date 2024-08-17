package com.example.travelapp.utils;

import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.Review;
import com.example.travelapp.entity.User;
import com.example.travelapp.repository.UserRepository;
import com.example.travelapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DTOConverter {

    @Autowired
    private HotelService hotelService;
    //@Autowired
    //private UserService userService;
    @Autowired
    private UserRepository userRepository;


    public User toEntity(UserDto userDto){
        User user = new User();

        List<Review> reviews = userDto
                .getReviewDtos().stream().map(this::toReviewEntity).toList();

        user.setReviews(reviews);
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());

        //user.setProfilePicture(userDto.getProfilePictureUrl());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toDto(User user){
        if (user == null) {
            return null;  // Handle the case when user is null
        }
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());

        if (user.getReviews() != null) {
            List<ReviewDto> reviewDtos = user
                    .getReviews().stream().map(this::toReviewDto).toList();
            userDto.setReviewDtos(reviewDtos);
        }
        //userDto.setProfilePictureUrl(user.getProfilePicture());
        //userDto.setProfilePicture(user.getProfilePicture());

        userDto.setEnabled(user.isEnabled());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    public Review toReviewEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());

        User user = userRepository.findUserById(reviewDto.getUserId());
        Hotel hotel = hotelService.findById(reviewDto.getHotelId());

        review.setUser(user);
        review.setHotel(hotel);
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setPublishedDate(reviewDto.getPublishedDate());

        return review;
    }
    public ReviewDto toReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();

        User user = userRepository.findUserById(reviewDto.getUserId());
        UserDto userDto = toDto(user);

        //reviewDto.setUsername(userDto.getUsername());
        //reviewDto.setProfilePicture(userDto.get);

        reviewDto.setProfilePicture(review.getUser().getProfilePicture());
        reviewDto.setUsername(review.getUser().getUsername());
        reviewDto.setUserId(review.getUser().getId()); // userD
        reviewDto.setHotelId(review.getHotel().getId()); // hotel

        reviewDto.setId(review.getId());
        reviewDto.setRating(review.getRating());
        reviewDto.setComment(review.getComment());
        reviewDto.setPublishedDate(review.getPublishedDate());

        return reviewDto;
    }


}

















