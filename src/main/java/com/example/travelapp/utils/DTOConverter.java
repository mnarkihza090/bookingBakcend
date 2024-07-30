package com.example.travelapp.utils;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DTOConverter {


    public User toEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());


        user.setProfilePicture(userDto.getProfilePictureUrl());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());

        userDto.setProfilePictureUrl(user.getProfilePicture());
        //userDto.setProfilePicture(user.getProfilePicture());
        userDto.setEnabled(user.isEnabled());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }


}

















