package com.example.travelapp.service;

import com.example.travelapp.entity.Payment;

public interface PaymentService {
    boolean processPayment(Payment payment);
    void savePayment(Payment payment);
}
