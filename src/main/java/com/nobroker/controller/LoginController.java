package com.nobroker.controller;

import com.nobroker.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private EmailVerificationService emailVerificationService;

    //http://localhost:8080/api/send-otp-for-login?email=kumarkirti224@gmail.com
    @PostMapping("/send-otp-for-login")
    public Map<String, String> sendOtpForLogin(@RequestParam String email) {
        Map<String, String> response = emailVerificationService.sendOtpForLogin(email);
        return response;
    }
    @PostMapping("/verify-otp-for-login")
    public Map<String,String>verifyOtpLogin(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtpForLogin(email,otp);
    }
}
