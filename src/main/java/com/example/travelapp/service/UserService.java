package com.example.travelapp.service;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.VerificationCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto,MultipartFile profilePicture);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    List<UserDto> getUsers();
    boolean verifyUser(String token);
    UserDto findByPhoneNumber(String phoneNumber);
    void saveResetCode(Long userId,String resetCode);


    VerificationCode findVerificationCode(Long userId, String resetCode);

    void updatePassword(Long userId, String newPassword);
}
