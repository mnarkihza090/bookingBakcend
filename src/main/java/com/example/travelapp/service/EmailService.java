package com.example.travelapp.service;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;

public interface EmailService {
    void sendVerificationEmail(User user, String token);
    void sendResetPasswordEmail(String email, String resetCode);
}
