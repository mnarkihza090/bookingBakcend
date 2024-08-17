package com.example.travelapp.controller;

import com.example.travelapp.dto.JwtResponse;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import com.example.travelapp.entity.VerificationCode;
import com.example.travelapp.entity.VerificationToken;
import com.example.travelapp.repository.VerificationTokenRepository;
import com.example.travelapp.request.ResetPasswordRequest;
import com.example.travelapp.request.SendResetCodeRequest;
import com.example.travelapp.request.VerifyResetCodeRequest;
import com.example.travelapp.service.*;
import com.example.travelapp.utils.DTOConverter;
import com.example.travelapp.utils.JwtProvider;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DTOConverter dtoConverter;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @PostMapping("/send-reset-code")
    public ResponseEntity<?> sendResetCode(@RequestBody SendResetCodeRequest resetCodeRequest){
        UserDto userDto = userService.findByPhoneNumber(resetCodeRequest.getPhoneNumber());

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this phone number");
        }

        // we create random number with 6 numbers
        String resetCode = String.valueOf(new Random().nextInt(899999) + 100000);
        userService.saveResetCode(userDto.getId(),resetCode);
        smsService.sendSms(resetCodeRequest.getPhoneNumber(),"Your reset code is: " + resetCode);

        return ResponseEntity.ok("Reset code sent successfully");
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<?> verifyResetCode(@RequestBody VerifyResetCodeRequest request){
        UserDto userDto = userService.findByPhoneNumber(request.getPhoneNumber());

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this phone number");
        }

        VerificationCode code = userService.findVerificationCode(userDto.getId(), request.getResetCode());

        if (code == null || code.isExpired()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset code");
        }

        return ResponseEntity.ok("Reset code verified successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request){
        UserDto userDto = userService.findByPhoneNumber(request.getPhoneNumber());

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this phone number");
        }

        VerificationCode code = userService.findVerificationCode(userDto.getId(), request.getResetCode());

        if (code == null || code.isExpired()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset code");
        }
        userService.updatePassword(userDto.getId(),request.getNewPassword());

        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPost (@RequestBody UserDto userDto) {
        log.info("Login attempt for email: {}", userDto.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            if (!userDetails.getUser().isEnabled()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please, firstly verify your email address");
            }

            String jwt = jwtProvider.generateToken(authentication);

            log.info("Login successful for email: {}", userDto.getEmail());
            return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @Transactional
    @PostMapping("/resend-verification")
    public ResponseEntity<?> resendVerificationEmail(@RequestParam("email") String email){
        UserDto userDto = userService.findByEmail(email);

        if (userDto == null){
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = dtoConverter.toEntity(userDto);
        String token = UUID.randomUUID().toString();
        VerificationToken existToken = verificationTokenRepository.findByUserId(user.getId());

        if (existToken != null){
            log.info("Existing verification token found for user: {}", existToken);
            existToken.setToken(token);
            existToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
            verificationTokenRepository.save(existToken);
            emailService.sendVerificationEmail(user,token);
        } else {
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUser(user);
            verificationToken.setExpiryDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
            verificationTokenRepository.save(verificationToken);
            emailService.sendVerificationEmail(user,token);
        }


        return ResponseEntity.ok("sent verification email, Please, control your email address");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPost(@RequestPart("user") UserDto userDto,
                                          @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture){
        userService.createUser(userDto,profilePicture);
        return ResponseEntity.ok(Map.of("message", "Register successful"));
    }
    // http://localhost:8080/verify?token= ourToken
    @GetMapping("/verify")
    public ResponseEntity<?> confirmationUser(@RequestParam String token,
                                   Model model){
        boolean isVerified = userService.verifyUser(token);
        if (isVerified){
            return ResponseEntity.ok("User verified successfully");
        }else{
            return ResponseEntity.badRequest().body("Verification token is invalid or expired");
        }
    }

    @GetMapping("/uploads/{fileType}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename,
                                             @PathVariable String fileType) {

        Resource file = fileStorageService.loadFile(filename,fileType);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfileInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser= (UserDetailsImpl) authentication.getPrincipal();

        UserDto userDto = userService.findByEmail(currentUser.getUser().getEmail());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
