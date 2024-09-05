package com.example.travelapp.service.impl;

import com.example.travelapp.dto.FlightBookingDto;
import com.example.travelapp.dto.FlightDto;
import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.*;
import com.example.travelapp.enums.*;
import com.example.travelapp.exceptions.BookingFailedException;
import com.example.travelapp.exceptions.PaymentFailedException;
import com.example.travelapp.exceptions.ResourceNotFound;
import com.example.travelapp.repository.FlightBookingRepository;
import com.example.travelapp.repository.RoomBookingRepository;
import com.example.travelapp.response.RoomBookingResponse;
import com.example.travelapp.service.*;
import com.example.travelapp.utils.DTOConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoomBookingRepository roomBookingRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private FlightBookingRepository flightBookingRepository;
    @Autowired
    private PricingService pricingService;

    @Transactional
    @Override
    public RoomBookingDto bookRoom(Long userId, Long roomId, RoomBookingDto bookingRequest) {
        RoomBooking roomBooking = new RoomBooking();

        try {
            UserDto userDto = userService.findByUserId(userId);
            Room room = roomService.findRoomByRoomId(roomId);

            if (userDto == null) {
                throw new ResourceNotFound("User not found");
            }

            if (room == null){
                throw new ResourceNotFound("Room not found");
            }


            roomBooking.setUser(dtoConverter.toEntity(userDto));
            roomBooking.setRoom(room);

            // Booking address
            roomBooking.setFirstName(bookingRequest.getFirstName());
            roomBooking.setLastName(bookingRequest.getLastName());
            roomBooking.setEmail(bookingRequest.getEmail());
            roomBooking.setPhoneNumber(bookingRequest.getPhoneNumber());
            roomBooking.setState(bookingRequest.getState());
            roomBooking.setCity(bookingRequest.getCity());
            roomBooking.setCountry(bookingRequest.getCountry());


            roomBooking.setAdults(bookingRequest.getAdults());
            roomBooking.setChildren(bookingRequest.getChildren());
            roomBooking.setInfant(bookingRequest.getInfant());
            roomBooking.setCheckInDate(bookingRequest.getCheckInDate());
            roomBooking.setCheckOutDate(bookingRequest.getCheckOutDate());
            String referenceNumber = generateReferenceNumber();
            roomBooking.setBookingReferenceNumber(referenceNumber);
            roomBooking.setNote(bookingRequest.getNote());
            roomBooking.setCreatedDate(LocalDate.now());
            roomBooking.setBookingType("Room");
            Payment payment = new Payment();
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentAmount(roomBooking.calculateTotalPrice());

            switch (bookingRequest.getPaymentType()){
                case CREDIT_CARD:
                    payment.setPaymentType(PaymentType.CREDIT_CARD);
                    payment.setCardNumber(bookingRequest.getCardNumber());
                    payment.setCardExpiryDate(bookingRequest.getCardExpiryDate());
                    payment.setCardHolderName(bookingRequest.getCardHolderName());
                    payment.setCardSecurityCode(bookingRequest.getCardSecurityCode());
                    break;
                case PAYPAL:
                    payment.setPaymentType(PaymentType.PAYPAL);
                    payment.setPaypalEmail(bookingRequest.getPaypalEmail());
                    break;
                case CASH:
                    payment.setPaymentType(PaymentType.CASH);
                    break;
            }

            payment.setRoomBooking(roomBooking);
            roomBooking.setPayment(payment);

            if (!paymentService.processPayment(dtoConverter.toPaymentDto(payment))) {
                throw new PaymentFailedException("Payment failed for booking reference: " +
                        roomBooking.getBookingReferenceNumber());
            }

            payment.setPaymentStatus(PaymentStatus.PAID);
            roomBooking.setBookingStatus(BookingStatus.CONFIRMED);

            room.setRoomStatus(RoomStatus.RESERVED);
            bookingRequest.setBookingReferenceNumber(referenceNumber);
            bookingRequest.setTotalPrice(payment.getPaymentAmount());
            bookingRequest.setBookingStatus(roomBooking.getBookingStatus());

            roomBookingRepository.save(roomBooking);

        } catch (Exception e) {
            throw new BookingFailedException("Booking failed: " + e.getMessage(),e);
        }
        return bookingRequest;
    }

    public String generateReferenceNumber(){
        Random random = new SecureRandom();
        int reference = random.nextInt(900000) + 100000;
        return String.valueOf(reference);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        try {
            RoomBooking roomBooking = roomBookingRepository.findById(bookingId).orElse(null);

            if (roomBooking == null) {
                throw new ResourceNotFound("Booking not found");
            }

            roomBooking.setBookingStatus(BookingStatus.CANCELLED);

            roomBookingRepository.save(roomBooking);

        } catch (Exception e) {
            throw new BookingFailedException("Booking cancellation failed: " + e.getMessage(),e);
        }
    }

    @Override
    public void calculateTotalPrice(RoomBookingDto bookingRequest) {

    }

    @Override
    public String getBookingReferenceNumber(Long bookingId) {
        RoomBooking roomBooking = roomBookingRepository.findById(bookingId).orElse(null);
        assert roomBooking != null;
        return roomBooking.getBookingReferenceNumber();
    }

    @Override
    public Page<RoomBookingDto> findByUserId(Pageable pageable,Long userId) {
        Page<RoomBooking> myBookings = roomBookingRepository.findByUserId(pageable,userId);

        Page<RoomBookingDto> myBooks =
                myBookings
                        .map(booking -> dtoConverter.toRoombookingDto(booking));

        return myBooks;
    }

    @Transactional
    @Override
    public FlightBookingDto bookFlight(Long userId, Long flightId, FlightBookingDto bookingDto) {

        UserDto userDto = userService.findByUserId(userId);
        FlightDto flightDto = flightService.findById(flightId);
        FlightBooking flightBooking = new FlightBooking();

        if (flightDto == null){
            throw new ResourceNotFound("Flight not found");
        }

        /*BigDecimal totalPrice =
                ticket.calculateFinalPrice(bookingDto.getAdult(), bookingDto.getChildren(), bookingDto.getInfant()); */

        flightBooking.setTicketType(bookingDto.getTicketType());
        flightBooking.setFlightClass(bookingDto.getFlightClass());
        flightBooking.setAdult(bookingDto.getAdult());
        flightBooking.setChildren(bookingDto.getChildren());
        flightBooking.setInfant(bookingDto.getInfant());

        BigDecimal totalPrice =
                PricingService.calculateTotalPrice(bookingDto.getFlightClass(),bookingDto.getTicketType(), bookingDto.getAdult(), bookingDto.getChildren(), bookingDto.getInfant());

        flightBooking.setFlight(dtoConverter.toFlightEntity(flightDto));
        flightBooking.setUser(dtoConverter.toEntity(userDto));
        flightBooking.setFlightNumber(flightDto.getFlightNumber());
        flightBooking.setBookingStatus(BookingStatus.CONFIRMED);

        flightBooking.setTravelDate(bookingDto.getTravelDate());
        flightBooking.setAirline(bookingDto.getAirline());
        flightBooking.setDepartureAirport(flightDto.getFromAirport());
        flightBooking.setArrivalAirport(flightDto.getFromAirport());
        flightBooking.setDepartureDate(flightDto.getDepartureTime());
        flightBooking.setArrivalDate(flightDto.getArrivalTime());
        flightBooking.setPassengerFirstName(bookingDto.getPassengerFirstName());
        flightBooking.setPassengerPassportCountry(bookingDto.getPassengerPassportCountry());
        flightBooking.setPassengerTitle(bookingDto.getPassengerTitle());
        flightBooking.setPassengerNationality(bookingDto.getPassengerNationality());
        flightBooking.setPassportExpiry(bookingDto.getPassengerPasswordExpiry());
        flightBooking.setPassengerLastName(bookingDto.getPassengerLastName());
        flightBooking.setPassengerEmail(bookingDto.getPassengerEmail());
        flightBooking.setPassengerPhoneNumber(bookingDto.getPassengerPhoneNumber());
        flightBooking.setPassengerDateOfBirth(bookingDto.getPassengerDateOfBirth());
        flightBooking.setPassengerPassportNumber(bookingDto.getPassengerPassportNumber());
        flightBooking.setBookingDate(LocalDateTime.now());
        flightBooking.setTotalPrice(totalPrice);
        //flightBooking.setTotalPrice(totalPrice);

        String bookingReference = generateBookingReference();
        flightBooking.setBookingReferenceNumber(bookingReference);


        flightBookingRepository.save(flightBooking);

        Payment payment = new Payment();
        payment.setFlightBooking(flightBooking);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setPaymentAmount(totalPrice);
        payment.setPaymentType(bookingDto.getPaymentType());
        payment.setCardNumber(bookingDto.getCardNumber());
        payment.setCardExpiryDate(bookingDto.getCardExpiryDate());
        payment.setCardHolderName(bookingDto.getCardHolderName());
        payment.setCardSecurityCode(bookingDto.getCardSecurityCode());

        paymentService.savePayment(dtoConverter.toPaymentDto(payment));

        flightBooking.setPayment(payment);



        return dtoConverter.toFlightBookingDto(flightBooking);
    }

    @Override
    public List<FlightBookingDto> findFlightBookingsByUserId(Long userId) {
        List<FlightBooking> flightBookings = flightBookingRepository.findFlightBookingsByUserId(userId);

        if (flightBookings.isEmpty()){
            throw new ResourceNotFound("No flight bookings found for user");
        }

        List<FlightBookingDto> flightBookingDtos = flightBookings.stream()
                .map(booking -> dtoConverter.toFlightBookingDto(booking)).toList();

        return flightBookingDtos;
    }

    private String generateBookingReference() {
        return UUID.randomUUID().toString();
    }
}
























