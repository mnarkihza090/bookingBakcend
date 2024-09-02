package com.example.travelapp.service;

import com.example.travelapp.dto.PaymentDto;
import com.example.travelapp.entity.Payment;
import com.example.travelapp.request.PaymentRequest;

public interface PaymentService {
    boolean processPayment(PaymentDto paymentDto);
    void savePayment(PaymentDto paymentDto);
}
