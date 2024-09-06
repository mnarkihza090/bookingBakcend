package com.example.travelapp.utils;

import com.example.travelapp.dto.*;
import com.example.travelapp.entity.*;
import com.example.travelapp.repository.*;
import com.example.travelapp.response.RoomBookingResponse;
import com.example.travelapp.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DTOConverter {

    @Autowired
    private HotelRepository hotelRepository;
    //@Autowired
    //private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomBookingRepository roomBookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RoomRepository roomRepository;


    public Hotel toHotelEntity(HotelDto hotelDto){
        Hotel hotel = new Hotel();
        hotel.setId(hotelDto.getId());
        hotel.setName(hotelDto.getName());
        hotel.setImages(hotelDto.getImages());
        hotel.setLocation(hotelDto.getLocation());
        hotel.setOriginalPrice(hotelDto.getOriginalPrice());
        hotel.setRefundable(hotelDto.isRefundable());
        hotel.setDiscountedPrice(hotelDto.getDiscountedPrice());
        hotel.setDiscountPercentage(hotelDto.getDiscountPercentage());

        hotel.setCountry(hotelDto.getCountry());
        hotel.setAmenities(hotelDto.getAmenities());

        List<Room> rooms = hotelDto.getRooms().stream().map(this::toRoomEntity).toList();

        hotel.setRooms(rooms);

        List<Review> reviews =
                hotelDto.getReviews().stream().map(this::toReviewEntity).toList();
        hotel.setReviews(reviews.isEmpty() ? null : reviews);
        hotel.setAverageRating(hotelDto.getAverageRating());

        return hotel;
    }
    public HotelDto toHotelDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());
        hotelDto.setImages(hotel.getImages());
        hotelDto.setLocation(hotel.getLocation());
        hotelDto.setOriginalPrice(hotel.getOriginalPrice());
        hotelDto.setRefundable(hotel.isRefundable());
        hotelDto.setDiscountedPrice(hotel.getDiscountedPrice());
        hotelDto.setDiscountPercentage(hotel.getDiscountPercentage());
        hotelDto.setCountry(hotel.getCountry());
        hotelDto.setAmenities(hotel.getAmenities());

        List<RoomDto> roomDtos = hotel.getRooms().stream().map(this::toRoomDto).toList();

        hotelDto.setRooms(roomDtos);

        List<ReviewDto> reviewDtos =
                hotel.getReviews().stream().map(this::toReviewDto).toList();

        hotelDto.setReviews(reviewDtos.isEmpty() ? null : reviewDtos);

        hotelDto.setAverageRating(hotel.getAverageRating());


        return hotelDto;
    }

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
        User user = userRepository.findUserById(reviewDto.getUserId());
        Hotel hotel = hotelRepository.findHotelById(reviewDto.getHotelId());

        review.setUser(user);
        review.setHotel(hotel);

        review.setId(reviewDto.getId());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        review.setPublishedDate(reviewDto.getPublishedDate());

        return review;
    }
    public ReviewDto toReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
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
        Hotel hotel = hotelRepository.findHotelById(roomDto.getHotelId());

        room.setId(roomDto.getId());
        room.setHotel(hotel);
        room.setImages(roomDto.getImages());
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomType(roomDto.getRoomType());
        room.setAmenities(roomDto.getAmenities());
        room.setLocation(roomDto.getLocation());
        room.setOriginalPrice(roomDto.getOriginalPrice());
        room.setDiscountedPrice(roomDto.getDiscountedPrice());
        room.setDiscountPercentage(roomDto.getDiscountPercentage());
        room.setCapacity(roomDto.getCapacity());
        room.setDescription(roomDto.getDescription());
        room.setAvailableFrom(roomDto.getAvailableFrom());
        room.setAvailableUntil(roomDto.getAvailableUntil());

        return room;
    }

    public RoomDto toRoomDto(Room room){
        RoomDto roomDto = new RoomDto();
        Hotel hotel = hotelRepository.findHotelById(room.getHotel().getId());

        roomDto.setId(room.getId());
        roomDto.setImages(room.getImages());
        roomDto.setAmenities(room.getAmenities());
        roomDto.setLocation(room.getLocation());
        roomDto.setAvailableFrom(room.getAvailableFrom());
        roomDto.setAvailableUntil(room.getAvailableUntil());
        roomDto.setHotelId(room.getHotel().getId());
        roomDto.setRoomNumber(room.getRoomNumber());
        roomDto.setRoomType(room.getRoomType());
        roomDto.setOriginalPrice(room.getOriginalPrice());
        roomDto.setDiscountedPrice(room.getDiscountedPrice());
        roomDto.setDiscountPercentage(room.getDiscountPercentage());
        roomDto.setCapacity(room.getCapacity());
        roomDto.setDescription(room.getDescription());

        return roomDto;
    }

    public Payment toPaymentEntity(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setPaymentType(paymentDto.getPaymentType());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentAmount(paymentDto.getPaymentAmount());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setCardExpiryDate(paymentDto.getCardExpiryDate());
        payment.setCardHolderName(paymentDto.getCardHolderName());
        payment.setCardSecurityCode(paymentDto.getCardSecurityCode());
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setPaypalEmail(paymentDto.getPaypalEmail());

        return payment;
    }

    public PaymentDto toPaymentDto(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setCardNumber(payment.getCardNumber());
        paymentDto.setPaymentType(payment.getPaymentType());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentAmount(payment.getPaymentAmount());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setCardExpiryDate(payment.getCardExpiryDate());
        paymentDto.setCardHolderName(payment.getCardHolderName());
        paymentDto.setCardSecurityCode(payment.getCardSecurityCode());
        paymentDto.setCardNumber(payment.getCardNumber());
        paymentDto.setPaypalEmail(payment.getPaypalEmail());
        return paymentDto;
    }

    public RoomBooking toRoomBooking(RoomBookingDto roomBookingDto){
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setCountry(roomBookingDto.getCountry());
        roomBooking.setCity(roomBookingDto.getCity());
        roomBooking.setState(roomBookingDto.getState());
        roomBooking.setFirstName(roomBookingDto.getFirstName());
        roomBooking.setLastName(roomBookingDto.getLastName());
        roomBooking.setNote(roomBookingDto.getNote());
        roomBooking.setEmail(roomBookingDto.getEmail());
        roomBooking.setPhoneNumber(roomBookingDto.getPhoneNumber());
        roomBooking.setCheckInDate(roomBookingDto.getCheckInDate());
        roomBooking.setCheckOutDate(roomBookingDto.getCheckOutDate());



        roomBooking.setBookingReferenceNumber(roomBookingDto.getBookingReferenceNumber());


        return roomBooking;
    }
    public RoomBookingDto toRoombookingDto(RoomBooking roomBooking){
        RoomBookingDto roomBookingDto = new RoomBookingDto();
        roomBookingDto.setBookingId(roomBooking.getBookingId());
        roomBookingDto.setCountry(roomBooking.getCountry());
        roomBookingDto.setCity(roomBooking.getCity());
        roomBookingDto.setState(roomBooking.getState());
        roomBookingDto.setFirstName(roomBooking.getFirstName());
        roomBookingDto.setLastName(roomBooking.getLastName());
        roomBookingDto.setNote(roomBooking.getNote());
        roomBookingDto.setEmail(roomBooking.getEmail());
        roomBookingDto.setPhoneNumber(roomBooking.getPhoneNumber());
        roomBookingDto.setCheckInDate(roomBooking.getCheckInDate());
        roomBookingDto.setCheckOutDate(roomBooking.getCheckOutDate());
        roomBookingDto.setBookingReferenceNumber(roomBooking.getBookingReferenceNumber());

        roomBookingDto.setUserId(roomBooking.getUser().getId());
        roomBookingDto.setRoomId(roomBooking.getRoom().getId());
        roomBookingDto.setEmail(roomBooking.getEmail());
        roomBookingDto.setCity(roomBooking.getCity());
        roomBookingDto.setBookingStatus(roomBooking.getBookingStatus());
        roomBookingDto.setAdults(roomBooking.getAdults());
        roomBookingDto.setChildren(roomBooking.getChildren());
        roomBookingDto.setInfant(roomBooking.getInfant());
        roomBookingDto.setBookingType(roomBooking.getBookingType());
        roomBookingDto.setTotalPrice(roomBooking.getTotalPrice());

        return roomBookingDto;
    }

    public RoomBookingResponse roomBookingResponse(RoomBookingDto roomBookingDto){
        RoomBookingResponse roomBookingResponse = new RoomBookingResponse();



        roomBookingResponse.setFirstName(roomBookingDto.getFirstName());
        roomBookingResponse.setLastName(roomBookingDto.getLastName());
        roomBookingResponse.setEmail(roomBookingDto.getEmail());
        roomBookingResponse.setPhoneNumber(roomBookingDto.getPhoneNumber());
        roomBookingResponse.setCity(roomBookingDto.getCity());
        roomBookingResponse.setCountry(roomBookingDto.getCountry());
        roomBookingResponse.setState(roomBookingDto.getState());
        roomBookingResponse.setCreatedDate(LocalDate.now());
        roomBookingResponse.setPaymentType(roomBookingDto.getPaymentType());
        roomBookingResponse.setAddress(roomBookingDto.getAddress());
        roomBookingResponse.setLastUpdatedDate(LocalDate.now());

        roomBookingResponse.setBookingStatus(roomBookingDto.getBookingStatus());
        roomBookingResponse.setBookingReferenceNumber(roomBookingDto.getBookingReferenceNumber());
        roomBookingResponse.setTotalPrice(roomBookingDto.getTotalPrice());

        return roomBookingResponse;
    }

    public FlightDto toFlightDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightId(flight.getFlightId());
        flightDto.setAirlineName(flight.getAirlineName());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setFromAirport(flight.getFromAirport());
        flightDto.setToAirport(flight.getToAirport());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setOriginalPrice(flight.getOriginalPrice());
        flightDto.setDiscountedPrice(flight.getDiscountedPrice());
        flightDto.setDiscountPercentage(flight.getDiscountPercentage());
        flightDto.setRefundPolicy(flight.getRefundPolicy());
        flightDto.setAirlineLogo(flight.getAirlineLogo());
        flightDto.setFromCity(flight.getFromCity());
        flightDto.setToCity(flight.getToCity());
        flightDto.setAirplaneType(flight.getAirplaneType());
        flightDto.setFlightDuration(flight.getFlightDuration());
        flightDto.setBaggageInfo(flight.getBaggageInfo());
        flightDto.setRefundable(flight.isRefundable());
        flightDto.setSeatAvailability(flight.getSeatAvailability());

        return flightDto;
    }
    public Flight toFlightEntity(FlightDto flightDto){
        Flight flight = new Flight();
        flight.setFlightId(flightDto.getFlightId());
        flight.setAirlineName(flightDto.getAirlineName());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setFromAirport(flightDto.getFromAirport());
        flight.setToAirport(flightDto.getToAirport());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        flight.setOriginalPrice(flightDto.getOriginalPrice());
        flight.setDiscountedPrice(flightDto.getDiscountedPrice());
        flight.setDiscountPercentage(flightDto.getDiscountPercentage());
        flight.setRefundPolicy(flightDto.getRefundPolicy());
        flight.setAirlineLogo(flightDto.getAirlineLogo());

        return flight;
    }

    public FlightBooking toFlightBooking(FlightBookingDto flightBookingDto){
        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setPassengerFirstName(flightBookingDto.getPassengerFirstName());
        flightBooking.setPassengerLastName(flightBookingDto.getPassengerLastName());
        flightBooking.setPassengerEmail(flightBookingDto.getPassengerEmail());
        flightBooking.setBookingId(flightBookingDto.getBookingId());
        flightBooking.setPassportExpiry(flightBookingDto.getPassengerPasswordExpiry());
        flightBooking.setPassengerPassportNumber(flightBookingDto.getPassengerPassportNumber());
        flightBooking.setPassengerPhoneNumber(flightBookingDto.getPassengerPhoneNumber());
        flightBooking.setArrivalDate(flightBookingDto.getArrivalDate());
        flightBooking.setDepartureDate(flightBookingDto.getDepartureDate());
        flightBooking.setArrivalAirport(flightBookingDto.getArrivalAirport());
        flightBooking.setDepartureAirport(flightBookingDto.getDepartureAirport());
        flightBooking.setFlightNumber(flightBookingDto.getFlightNumber());
        flightBooking.setAdult(flightBookingDto.getAdult());
        flightBooking.setChildren(flightBookingDto.getChildren());
        flightBooking.setInfant(flightBookingDto.getInfant());
        flightBooking.setPassengerPassportCountry(flightBookingDto.getPassengerPassportCountry());
        flightBooking.setPassengerTitle(flightBookingDto.getPassengerTitle());
        flightBooking.setPassengerDateOfBirth(flightBookingDto.getPassengerDateOfBirth());

        return flightBooking;
    }

    public FlightBookingDto toFlightBookingDto(FlightBooking flightBooking){
        FlightBookingDto flightBookingDto = new FlightBookingDto();

        flightBookingDto.setFlightId((long) flightBooking.getFlight().getFlightId());
        flightBookingDto.setTravelDate(flightBooking.getTravelDate());
        flightBookingDto.setFlightClass(flightBooking.getFlightClass());
        flightBookingDto.setTicketType(flightBooking.getTicketType());
        flightBookingDto.setBookingReferenceNumber(flightBooking.getBookingReferenceNumber());
        flightBookingDto.setBookingStatus(String.valueOf(flightBooking.getBookingStatus()));
        flightBookingDto.setBookingDate(flightBooking.getBookingDate());
        flightBookingDto.setTotalPrice(flightBooking.getTotalPrice());
        flightBookingDto.setPassengerPasswordExpiry(flightBooking.getPassportExpiry());
        flightBookingDto.setAirline(flightBooking.getAirline());
        flightBookingDto.setPassengerTitle(flightBooking.getPassengerTitle());
        flightBookingDto.setPassengerNationality(flightBooking.getPassengerNationality());
        flightBookingDto.setPaymentType(flightBooking.getPayment().getPaymentType());
        flightBookingDto.setPassengerPassportCountry(flightBooking.getPassengerPassportCountry());
        flightBookingDto.setCardExpiryDate(flightBooking.getPayment().getCardExpiryDate());
        flightBookingDto.setCardNumber(flightBooking.getPayment().getCardNumber());
        flightBookingDto.setCardHolderName(flightBooking.getPayment().getCardHolderName());
        flightBookingDto.setPaymentStatus(flightBooking.getPayment().getPaymentStatus());
        flightBookingDto.setCardSecurityCode(flightBooking.getPayment().getCardSecurityCode());

        flightBookingDto.setPaymentDate(flightBooking.getPayment().getPaymentDate());


        flightBookingDto.setUserId(flightBooking.getUser().getId());
        flightBookingDto.setPassengerFirstName(flightBooking.getPassengerFirstName());
        flightBookingDto.setPassengerLastName(flightBooking.getPassengerLastName());
        flightBookingDto.setPassengerEmail(flightBooking.getPassengerEmail());
        flightBookingDto.setBookingId(flightBooking.getBookingId());
        flightBookingDto.setPassengerPassportNumber(flightBooking.getPassengerPassportNumber());
        flightBookingDto.setPassengerPhoneNumber(flightBooking.getPassengerPhoneNumber());
        flightBookingDto.setArrivalDate(flightBooking.getArrivalDate());
        flightBookingDto.setDepartureDate(flightBooking.getDepartureDate());
        flightBookingDto.setArrivalAirport(flightBooking.getArrivalAirport());
        flightBookingDto.setDepartureAirport(flightBooking.getFlight().getFromAirport());
        flightBookingDto.setFlightNumber(flightBooking.getFlightNumber());
        flightBookingDto.setPassengerPassportCountry(flightBooking.getPassengerPassportCountry());
        flightBookingDto.setPassengerTitle(flightBooking.getPassengerTitle());
        flightBookingDto.setPassengerDateOfBirth(flightBooking.getPassengerDateOfBirth());

        return flightBookingDto;
    }
}

















