package com.example.travelapp.dto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    //private MultipartFile profilePicture; // for uploading image file
    //private String profilePictureUrl; // for displaying image
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isEnabled;
    private List<ReviewDto> reviewDtos = new ArrayList<>();
    @Transient
    private String confirmPassword;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstName, String lastName, String email, String password, String phoneNumber, boolean isEnabled, List<ReviewDto> reviewDtos, String confirmPassword) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isEnabled = isEnabled;
        this.reviewDtos = reviewDtos;
        this.confirmPassword = confirmPassword;
    }
}
