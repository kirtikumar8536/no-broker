package com.nobroker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

import static com.nobroker.service.EmailVerificationService.emailOtpMapping;

@Service
public class EmailService {
    private final UserService userService;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public String generateOtp() {
        //otp require for login
        //otp require for email
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }

    public Map<String, String> sendOtp(String email) {
        String otp = generateOtp();
        //save the otp for latter verification
        emailOtpMapping.put(email,otp);
        //send OTP TO USER'S EMAIL
        sendEmail(email, "OTP for Email verification", "Your OTP is:" + otp);
        Map<String,String> response=new HashMap<>();
        response.put("status","success");
        response.put("message","Otp send Successfully");
        return response;
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kirtikumar8536@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}
