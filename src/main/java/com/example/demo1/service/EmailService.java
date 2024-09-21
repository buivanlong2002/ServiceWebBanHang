package com.example.demo1.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleMail(String to, String subject, String content) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
       helper.setSubject(subject);
       helper.setText(content, true);
       mailSender.send(message);
    }

}
