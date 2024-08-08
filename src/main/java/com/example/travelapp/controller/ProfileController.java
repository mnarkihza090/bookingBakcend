package com.example.travelapp.controller;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.service.UserDetailsImpl;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser= (UserDetailsImpl) authentication.getPrincipal();

        UserDto userDto = userService.findByEmail(currentUser.getUser().getEmail());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}






















