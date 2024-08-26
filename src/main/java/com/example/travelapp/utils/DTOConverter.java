package com.example.travelapp.utils;

import com.example.travelapp.dto.ReviewDto;
import com.example.travelapp.dto.RoomDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.*;
import com.example.travelapp.repository.UserRepository;
import com.example.travelapp.request.PaymentRequest;
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

    public Room toRoomEntity(RoomDto roomDto){
        Room room = new Room();
        Hotel hotel = hotelService.findById(roomDto.getHotelId());

        room.setId(roomDto.getId());
        room.setHotel(hotel);
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomType(roomDto.getRoomType());
        room.setPricePerNight(roomDto.getPricePerNight());
        room.setCapacity(roomDto.getCapacity());
        room.setDescription(roomDto.getDescription());

        return room;
    }

    public RoomDto toRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        Hotel hotel = hotelService.findById(roomDto.getHotelId());

        roomDto.setId(room.getId());
        roomDto.setHotelId(hotel.getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setPricePerNight(room.getPricePerNight());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setDescription(room.getDescription());

        return roomDto;
    }

    public Payment toPaymentEntity(PaymentRequest paymentRequest){
        Payment payment = new Payment();
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setPaymentType(paymentRequest.getPaymentType());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setPaymentAmount(paymentRequest.getPaymentAmount());
        payment.setPaymentDate(paymentRequest.getPaymentDate());
        payment.setCardExpiryDate(paymentRequest.getCardExpiryDate());
        payment.setCardHolderName(paymentRequest.getCardHolderName());
        payment.setCardSecurityCode(paymentRequest.getCardSecurityCode());
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setPaypalEmail(payment.getPaypalEmail());

        return payment;
    }

    public PaymentRequest toPaymentDto(Payment payment){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardNumber(payment.getCardNumber());
        paymentRequest.setPaymentType(payment.getPaymentType());
        paymentRequest.setPaymentStatus(payment.getPaymentStatus());
        paymentRequest.setPaymentAmount(payment.getPaymentAmount());
        paymentRequest.setPaymentDate(payment.getPaymentDate());
        paymentRequest.setCardExpiryDate(payment.getCardExpiryDate());
        paymentRequest.setCardHolderName(payment.getCardHolderName());
        paymentRequest.setCardSecurityCode(payment.getCardSecurityCode());
        paymentRequest.setCardNumber(payment.getCardNumber());
        paymentRequest.setPaypalEmail(payment.getPaypalEmail());

        return paymentRequest;
    }

}

















