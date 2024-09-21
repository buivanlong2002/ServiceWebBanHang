package com.example.demo1.controller;

import com.example.demo1.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) throws MessagingException {
        emailService.sendSimpleMail(to, subject, text);
        return ResponseEntity.ok("Email sent successfully");
    }
    @GetMapping("/send")
    public String sendEmail() throws MessagingException {
        String to = "Longbui20021008@gmail.com";
        String subject = "Test Email";
        String text = "hello";

        emailService.sendSimpleMail(to, subject, text);
        return "Email sent successfully";
    }
}