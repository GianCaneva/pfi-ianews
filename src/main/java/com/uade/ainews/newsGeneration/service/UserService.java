package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.User;

import com.uade.ainews.newsGeneration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

/* todo password
    @Autowired
    private PasswordEncoder passwordEncoder;
*/

    public void registerUser(String email, String password) {
        // Check if the mail already exists
        if (userRepository.findByEmail(email) == null) {
            // Encoding the password before storing it in the database
            userRepository.save(User.builder().email(email).password(password).build());
        } else {
            throw new RuntimeException("The email is already registered");
        }
    }
}