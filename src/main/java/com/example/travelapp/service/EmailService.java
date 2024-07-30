package com.example.travelapp.service;

import com.example.travelapp.entity.User;

public interface EmailService {
    void sendVerificationEmail(User user, String token);
}
