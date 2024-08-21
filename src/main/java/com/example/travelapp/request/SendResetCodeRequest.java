package com.example.travelapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendResetCodeRequest {
    private String email;
}
