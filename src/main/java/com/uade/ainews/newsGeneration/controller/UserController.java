package com.uade.ainews.newsGeneration.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.security.Encoder;
import com.uade.ainews.newsGeneration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//development
//http://localhost:8080/news/user/register?email=gfocaneva@gmail.com&password=1234
//http://localhost:8080/news/user/all
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            String password = requestBody.get("password");
            Encoder encoder = Encoder.getInstance();
            userService.registerUser(email, encoder.encode(password));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("User created successfully");
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Object> subscribeNewsletter(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            Encoder encoder = Encoder.getInstance();
            userService.subscribeNewsletter(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("User subscribed to the newsletter.");
    }






//
//    @PostMapping("/register")
//    public void registerUser(@RequestParam String email, @RequestBody String password) {
//        Encoder encoder = Encoder.getInstance();
//        userService.registerUser(email, encoder.encode("admin"));
//    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }
}
