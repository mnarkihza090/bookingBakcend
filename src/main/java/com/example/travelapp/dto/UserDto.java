package com.example.travelapp.dto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    //private MultipartFile profilePicture; // for uploading image file
    //private String profilePictureUrl; // for displaying image
    private String email;
    private String password;
    private boolean isEnabled;
    @Transient
    private String confirmPassword;

    public UserDto() {
    }


}
