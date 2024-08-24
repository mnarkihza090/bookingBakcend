package com.example.travelapp.entity;

import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal paymentAmount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;

    @OneToOne
    @JoinColumn(name = "room_booking_id")
    private RoomBooking roomBooking;

    // Methods to process payment based on payment type
    public void processPayment() {
        switch (paymentType) {
            case CREDIT_CARD:
                processCreditCardPayment();
                break;
            case PAYPAL:
                processPayPalPayment();
                break;
            case CASH:
                processCashPayment();
                break;
        }
    }

    private void processCreditCardPayment() {
        // Logic to process credit card payment
    }

    private void processPayPalPayment() {
        // Logic to process PayPal payment
    }

    private void processCashPayment() {
        // Logic to process cash payment
    }
}























