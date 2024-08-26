package com.example.travelapp.request;

import com.example.travelapp.entity.RoomBooking;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentRequest {

    private Long id;
    private BigDecimal paymentAmount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;

    // Credit Card fields
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;

    // PayPal fields
    private String paypalEmail;

}
