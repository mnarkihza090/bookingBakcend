package com.example.travelapp.service.impl;

import com.example.travelapp.dto.UserDto;
import com.example.travelapp.entity.User;
import com.example.travelapp.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendVerificationEmail(User user, String token) {
        String subject = "M-Travel verification email";
        String senderName = "M-Travel";
        String confirmationLink = "http://localhost:8080/api/auth/verify?token=" + token;

        String mailContent = "<p> Dear " +user.getUsername() + ", </p>";
        mailContent += "<p>Please click the link below to verify your email </p>";
        mailContent += "<h3><a href=\"" + confirmationLink + "\">VERIFY</a></h3>";
        mailContent += "<p>Thank you <br> M-Travel </p>";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setFrom("admin@mtravel.com",senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}






















