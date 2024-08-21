package com.example.travelapp.service.impl;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Role;
import com.example.travelapp.entity.User;
import com.example.travelapp.entity.VerificationCode;
import com.example.travelapp.entity.VerificationToken;
import com.example.travelapp.exceptions.EmailNotFoundException;
import com.example.travelapp.repository.RoleRepository;
import com.example.travelapp.repository.UserRepository;
import com.example.travelapp.repository.VerificationCodeRepository;
import com.example.travelapp.repository.VerificationTokenRepository;
import com.example.travelapp.service.EmailService;
import com.example.travelapp.service.FileStorageService;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.DTOConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDto userDto,MultipartFile profilePicture) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(userDto.getConfirmPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEnabled(false);

        if (findByUsername(userDto.getUsername()) != null){
            throw new RuntimeException("Username already taken");
        }

        if (findByEmail(userDto.getEmail()) != null){
            throw new RuntimeException("Email already taken");
        }

        Role role = roleRepository.findByRoleName("ROLE_USER");

        if (role == null){
            Role newRole = new Role();
            newRole.setRoleName("ROLE_USER");
            roleRepository.save(newRole);
        }
        user.setRoles(Collections.singleton(role));

        //MultipartFile profilePicture = userDto.getProfilePicture();

        if (profilePicture != null && !profilePicture.isEmpty()){
            try {
                String fileName = fileStorageService.storeFile(profilePicture,"users");
                user.setProfilePicture(fileName);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Failed to upload profile picture");
            }
        }

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60* 1000));
        verificationTokenRepository.save(verificationToken);

        emailService.sendVerificationEmail(user,token);
    }


    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null){
            return null;
        }

        return dtoConverter.toDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null){
            return null;
        }

        return dtoConverter.toDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> result = users.stream().map(user -> dtoConverter.toDto(user)).toList();

        return result;
    }

    @Override
    public boolean verifyUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken != null){
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
            return true;
        }
        return false;
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber);

        if (user!= null){
            return dtoConverter.toDto(user);
        }
        return null;
    }

    @Override
    public void saveResetCode(Long userId, String resetCode) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setUserId(userId);
        verificationCode.setCode(resetCode);
        verificationCode.setExpiryDate(new Date(System.currentTimeMillis() + 10 *60 *10));

        VerificationCode existingCode = verificationCodeRepository.findByUserId(userId);
        if (existingCode != null){
            verificationCodeRepository.delete(existingCode);
        }


        verificationCodeRepository.save(verificationCode);
    }

    @Override
    public UserDto findByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.map(value -> dtoConverter.toDto(value)).orElse(null);
    }

    @Override
    public VerificationCode findVerificationCode(Long userId, String resetCode) {
        return verificationCodeRepository.findByUserIdAndCode(userId, resetCode);
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}























