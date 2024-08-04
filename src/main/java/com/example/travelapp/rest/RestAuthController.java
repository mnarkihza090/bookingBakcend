package com.example.travelapp.rest;

import com.example.travelapp.dto.JwtResponse;
import com.example.travelapp.dto.LoginDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import com.example.travelapp.service.FileStorageService;
import com.example.travelapp.service.UserDetailsImpl;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.JwtProvider;
import jakarta.validation.Valid;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class RestAuthController {
    private static final Logger log = LoggerFactory.getLogger(RestAuthController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> loginPost (@Valid @RequestBody UserDto userDto) {
        log.info("Login attempt for email: {}", userDto.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            log.info("Login successful for email: {}", userDto.getEmail());
            return ResponseEntity.ok(new JwtResponse(userDetails.getUser().getFirstName(),jwt,userDetails.getUsername(),userDetails.getUser().getProfilePicture()));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPost(@Valid @ModelAttribute UserDto userDto){
        userService.createUser(userDto);
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
