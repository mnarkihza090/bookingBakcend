package com.example.travelapp.service;

import com.example.travelapp.entity.Payment;
import com.example.travelapp.request.PaymentRequest;

public interface PaymentService {
    boolean processPayment(PaymentRequest paymentRequest);
    void savePayment(Payment payment);
}
