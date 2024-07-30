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
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3,max = 30,message = "Username must be between 3 and 50")
    private String username;
    @NotBlank(message = "First name is mandatory")
    @Size(max = 50,message = "First name must be less than 50")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50,message = "Last name must be less than 50")
    private String lastName;

    private MultipartFile profilePicture; // for uploading image file
    private String profilePictureUrl; // for displaying image

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    private boolean isEnabled;
    @Transient
    @NotBlank(message = "Confirm password is mandatory")
    private String confirmPassword;

    public UserDto() {
    }


}
