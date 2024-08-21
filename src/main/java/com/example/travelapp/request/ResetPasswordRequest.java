package com.example.travelapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String confirmPassword;
    private String newPassword;
}
