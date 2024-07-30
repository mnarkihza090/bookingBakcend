package com.example.travelapp.service;

import com.example.travelapp.dto.UserDto;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDto);
    UserDto findByUsername(String username);
    UserDto findByEmail(String email);
    List<UserDto> getUsers();
    boolean verifyUser(String token);

    boolean checkPassword(String password, String encodedPassword);
}
