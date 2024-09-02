package com.example.travelapp.controller;

import com.example.travelapp.dto.PaymentDto;
import com.example.travelapp.entity.Payment;
import com.example.travelapp.request.PaymentRequest;
import com.example.travelapp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDto paymentRequest){
        boolean success = paymentService.processPayment(paymentRequest);
        if (success) {
            return new ResponseEntity<>("Payment successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment failed!", HttpStatus.BAD_REQUEST);
        }
    }
}
