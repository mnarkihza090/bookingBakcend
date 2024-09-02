package com.example.travelapp.dto;

import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {

    private Long id;
    private BigDecimal paymentAmount;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;

    // Credit Card fields
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;

    // PayPal fields
    private String paypalEmail;
}
