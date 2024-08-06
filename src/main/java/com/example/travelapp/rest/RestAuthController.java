package com.example.travelapp.rest;

import com.example.travelapp.dto.JwtResponse;
import com.example.travelapp.dto.LoginDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import com.example.travelapp.entity.VerificationToken;
import com.example.travelapp.exceptions.EmailNotFoundException;
import com.example.travelapp.repository.VerificationTokenRepository;
import com.example.travelapp.service.EmailService;
import com.example.travelapp.service.FileStorageService;
import com.example.travelapp.service.UserDetailsImpl;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.DTOConverter;
import com.example.travelapp.utils.JwtProvider;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RestAuthController {
    private static final Logger log = LoggerFactory.getLogger(RestAuthController.class);
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
            return ResponseEntity.ok(new JwtResponse(userDetails.getUser().getFirstName(), jwt, userDetails.getUsername(), userDetails.getUser().getProfilePicture(), userDetails.getUser().isEnabled(),userDetails.getUser().getFirstName(),userDetails.getUser().getLastName(),userDetails.getPassword()));

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
}
