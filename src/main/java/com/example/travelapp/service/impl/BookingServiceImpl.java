package com.example.travelapp.service.impl;

import com.example.travelapp.dto.RoomDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.*;
import com.example.travelapp.enums.BookingStatus;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import com.example.travelapp.enums.RoomStatus;
import com.example.travelapp.exceptions.BookingFailedException;
import com.example.travelapp.exceptions.PaymentFailedException;
import com.example.travelapp.exceptions.ResourceNotFound;
import com.example.travelapp.mapper.PaymentMapper;
import com.example.travelapp.repository.HotelRepository;
import com.example.travelapp.repository.PaymentRepository;
import com.example.travelapp.repository.RoomBookingRepository;
import com.example.travelapp.repository.UserRepository;
import com.example.travelapp.request.BookingRequest;
import com.example.travelapp.service.*;
import com.example.travelapp.utils.DTOConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

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

    @Transactional
    @Override
    public void bookRoom(Long userId, Long roomId, BookingRequest bookingRequest) {
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
            roomBooking.setBookingReferenceNumber(generateReferenceNumber());
            roomBooking.setNote(bookingRequest.getNote());
            roomBooking.setCreatedDate(LocalDate.now());

            roomBooking.calculateTotalPrice();

            Payment payment = new Payment();
            payment.setPaymentDate(LocalDate.now());
            payment.setPaymentAmount(roomBooking.calculateTotalPrice());

            switch (bookingRequest.getPayment().getPaymentType()){
                case CREDIT_CARD:
                    payment.setPaymentType(PaymentType.CREDIT_CARD);
                    payment.setCardNumber(bookingRequest.getPayment().getCardNumber());
                    payment.setCardExpiryDate(bookingRequest.getPayment().getCardExpiryDate());
                    payment.setCardHolderName(bookingRequest.getPayment().getCardHolderName());
                    payment.setCardSecurityCode(bookingRequest.getPayment().getCardSecurityCode());
                    break;
                case PAYPAL:
                    payment.setPaymentType(PaymentType.PAYPAL);
                    payment.setPaypalEmail(bookingRequest.getPayment().getPaypalEmail());
                    break;
                case CASH:
                    payment.setPaymentType(PaymentType.CASH);
                    break;
            }

            payment.setRoomBooking(roomBooking);
            roomBooking.setPayment(payment);

            paymentService.processPayment(dtoConverter.toPaymentDto(payment));

            if (!paymentService.processPayment(dtoConverter.toPaymentDto(payment))) {
                throw new PaymentFailedException("Payment failed for booking reference: " + roomBooking.getBookingReferenceNumber());
            }

            payment.setPaymentStatus(PaymentStatus.PAID);
            roomBooking.setBookingStatus(BookingStatus.CONFIRMED);

            room.setRoomStatus(RoomStatus.RESERVED);

            roomBookingRepository.save(roomBooking);

        } catch (Exception e) {
            throw new BookingFailedException("Booking failed: " + e.getMessage(),e);
        }
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
    public void calculateTotalPrice(BookingRequest bookingRequest) {

    }
}
