package com.nobroker.controller;

import com.nobroker.entity.User;
import com.nobroker.service.EmailService;
import com.nobroker.service.EmailVerificationService;
import com.nobroker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailVerificationService emailVerificationService;

    //http://localhost:8080/api/register
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user) {
        //register the user without email verification
        User registerUser = userService.registerUser(user);
        //send OTP email for email verification
        Map<String, String> response = emailService.sendOtp(user.getEmail());
        return response;
    }
    //http://localhost:8080/api/verify-otp?email=&otp=
    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestParam String email,@RequestParam String otp){
        Map<String, String> isVerifiedEmailMapResponse = emailVerificationService.verifyOtp(email, otp);
        return isVerifiedEmailMapResponse;
    }
}