package com.example.travelapp.controller;

import com.example.travelapp.dto.JwtResponse;
import com.example.travelapp.dto.RoomBookingDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.Hotel;
import com.example.travelapp.entity.User;
import com.example.travelapp.entity.VerificationCode;
import com.example.travelapp.entity.VerificationToken;
import com.example.travelapp.repository.VerificationTokenRepository;
import com.example.travelapp.request.ResetPasswordRequest;
import com.example.travelapp.request.SendResetCodeRequest;
import com.example.travelapp.request.VerifyResetCodeRequest;
import com.example.travelapp.response.Pagination;
import com.example.travelapp.service.*;
import com.example.travelapp.utils.DTOConverter;
import com.example.travelapp.utils.JwtProvider;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import java.security.SecureRandom;
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
    private BookingService bookingService;


    @PostMapping("/send-reset-code")
    public ResponseEntity<?> sendResetCode(@RequestBody SendResetCodeRequest request){
        UserDto userDto = userService.findByEmail(request.getEmail());

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this email address");
        }
        // we create random number with 6 numbers
        String resetCode = generateResetCode();

        userService.saveResetCode(userDto.getId(),resetCode);
        emailService.sendResetPasswordEmail(request.getEmail(), resetCode);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<?> verifyResetCode(@RequestParam String email,
                                             @RequestBody VerifyResetCodeRequest request){
        UserDto userDto = userService.findByEmail(email);

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this email");
        }

        VerificationCode code =
                userService.findVerificationCode(userDto.getId(), request.getResetCode());

        if (code == null || code.isExpired()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset code");
        }

        return ResponseEntity.ok(code);
    }

    private String generateResetCode() {
        Random random = new SecureRandom();
        int resetCode = random.nextInt(900000) + 100000;
        return String.valueOf(resetCode);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email,
                                           @RequestBody ResetPasswordRequest request){
        UserDto userDto = userService.findByEmail(email);

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with this email");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New password and confirm password do not match");
        }

        userService.updatePassword(userDto.getId(),request.getNewPassword());

        return ResponseEntity.ok(request);
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

    @GetMapping("/profile/my-booking")
    @Transactional
    public ResponseEntity<?> getMyBooking(
            @RequestParam(defaultValue = "0",name = "page") int pageNumber,
            @RequestParam(defaultValue = "6",name = "size") int pageSize
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetailsImpl currentUser= (UserDetailsImpl) authentication.getPrincipal();
        String currentUser = authentication.getName();
        UserDto userDto = userService.findByEmail(currentUser);

        Page<RoomBookingDto> myBookings = bookingService.findByUserId(PageRequest.of(pageNumber,pageSize),userDto.getId());

        return ResponseEntity.ok(new Pagination<List<RoomBookingDto>>(
                myBookings.getTotalPages(),
                myBookings.getNumber(),
                myBookings.getSize(),
                myBookings.getContent(),
                myBookings.getTotalElements()));
    }
}
