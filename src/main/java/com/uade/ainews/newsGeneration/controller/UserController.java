package com.uade.ainews.newsGeneration.controller;

import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
//http://localhost:8080/news/user/all
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestParam String email, @RequestParam String password) {
        userService.registerUser(email, new BCryptPasswordEncoder().encode(password));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }
}
