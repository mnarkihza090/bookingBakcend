package com.example.travelapp.service.impl;

import com.example.travelapp.entity.Payment;
import com.example.travelapp.repository.PaymentRepository;
import com.example.travelapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean processPayment(Payment payment) {
        return true;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }
}
