package com.nobroker.service;

import com.nobroker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    static final Map<String, String> emailOtpMapping = new HashMap<>();
    @Autowired
    private UserService userService;

    public Map<String, String> verifyOtp(String email, String otp) {
        //logger.info("Recived Otp verification request for email:{},otp:{}",email,otp);
        String storedOtp = emailOtpMapping.get(email);
        // logger.info("stored Otp for email{} : {}",email,storedOtp);
        Map<String, String> response = new HashMap<>();
        //check otp validation and null
        if (storedOtp != null && storedOtp.equals(otp)) {
            //fetch user by email and mark email as verified
            //logger.info("Otp is valid.Proceeding with verification");
            User user = userService.getUserByEmail(email);
            if (user != null) {
                userService.verifyEmail(user);
                response.put("status", "success");
                response.put("message", "Email Verified successfully");
            } else {
                //logger.info("Invalid OTP recived for email: {},email")
                response.put("status", "error");
                response.put("message", "User not found");
            }
        } else {
             //logger.info("Invalid OTP recived for email: {},email")
            response.put("status", "failed");
            response.put("message", "Invalid Otp");
        }
        return response;
    }
}
