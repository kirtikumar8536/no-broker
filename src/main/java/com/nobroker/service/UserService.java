package com.nobroker.service;

import com.nobroker.entity.User;
import com.nobroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //save the user to the database
    public User registerUser(User user) {
        User savedEntity = userRepository.save(user);
        return savedEntity;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void verifyEmail(User user){
        user.setEmailVerified(true);
        userRepository.save(user);
    }
    public boolean isEmailVerified(String email){//called from Emailverification
        User user = userRepository.findByEmail(email);
        return user!=null && user.isEmailVerified();

    }
}
