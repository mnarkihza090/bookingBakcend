package com.example.travelapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String phoneNumber;
    private String resetCode;
    private String newPassword;
}
