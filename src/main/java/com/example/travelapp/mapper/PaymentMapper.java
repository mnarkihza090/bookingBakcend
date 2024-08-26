package com.example.travelapp.mapper;

import com.example.travelapp.entity.Payment;
import com.example.travelapp.request.PaymentRequest;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMapper {
    Payment toEntity(PaymentRequest paymentRequest);
    PaymentRequest toDto(Payment payment);
}
