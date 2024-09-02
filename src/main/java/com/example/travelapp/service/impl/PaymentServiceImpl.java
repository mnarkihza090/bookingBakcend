package com.example.travelapp.service.impl;

import com.example.travelapp.dto.PaymentDto;
import com.example.travelapp.entity.Payment;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import com.example.travelapp.repository.PaymentRepository;
import com.example.travelapp.request.PaymentRequest;
import com.example.travelapp.service.PaymentService;
import com.example.travelapp.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private DTOConverter dtoConverter;

    @Override
    public boolean processPayment(PaymentDto paymentRequest) {
        Payment payment = new Payment();
        try {
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentStatus(PaymentStatus.PAID);

            // Payment type-specific data handling
            switch (paymentRequest.getPaymentType()) {
                case CREDIT_CARD:
                    // Set credit card details
                    payment.setCardNumber(paymentRequest.getCardNumber());
                    payment.setCardHolderName(paymentRequest.getCardHolderName());
                    payment.setCardExpiryDate(paymentRequest.getCardExpiryDate());
                    payment.setCardSecurityCode(paymentRequest.getCardSecurityCode());
                    break;
                case PAYPAL:
                    // Set PayPal email
                    payment.setPaypalEmail(payment.getPaypalEmail());
                    break;
                case CASH:
                    // No additional information needed for cash
                    break;
                default:
                    throw new IllegalArgumentException("Unknown payment type: " + payment.getPaymentType());
            }

            // Save the payment to the database
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void savePayment(PaymentDto paymentDto) {
        paymentRepository.save(dtoConverter.toPaymentEntity(paymentDto));
    }

}
