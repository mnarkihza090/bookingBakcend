package com.example.travelapp.rest;

import com.example.travelapp.dto.JwtResponseDto;
import com.example.travelapp.dto.UserDto;
import com.example.travelapp.service.FileStorageService;
import com.example.travelapp.service.UserService;
import com.example.travelapp.utils.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RestAuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> loginPost(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));

        String jwt = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponseDto(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPost(@Valid @ModelAttribute("user") UserDto userDto,
                               @RequestParam(required = false,value = "success") String success,
                               BindingResult bindingResult,
                               Model model){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation failed");
        }
        userService.createUser(userDto);
        return ResponseEntity.ok("Register successful");
    }
    // http://localhost:8080/verify?token= ourToken
    @GetMapping("/verify")
    public String confirmationUser(@RequestParam String token,
                                   Model model){
        boolean isVerified = userService.verifyUser(token);
        if (isVerified){
            model.addAttribute("success","User verified successfully");
            return "redirect:/login";
        }else{
            model.addAttribute("error","Verification token is invalid or expired");
            return "redirect:/login";
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
