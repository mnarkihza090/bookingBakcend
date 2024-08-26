package com.example.travelapp.service.impl;

import com.example.travelapp.entity.Payment;
import com.example.travelapp.enums.PaymentStatus;
import com.example.travelapp.enums.PaymentType;
import com.example.travelapp.repository.PaymentRepository;
import com.example.travelapp.request.PaymentRequest;
import com.example.travelapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        try {
            payment.setPaymentDate(LocalDate.now());
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

    private void processCreditCardPayment(Payment payment) {

        System.out.println("Processing credit card payment...");
    }

    private void processPayPalPayment(Payment payment) {
        // PayPal işlemi için API çağrısı veya diğer mantık buraya eklenebilir
        System.out.println("Processing PayPal payment...");
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }
}
