package com.uade.ainews.newsGeneration.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.ainews.newsGeneration.dto.SummarizedNews;
import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.dto.UserStats;
import com.uade.ainews.newsGeneration.security.Encoder;
import com.uade.ainews.newsGeneration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        return ResponseEntity.ok().body("User created successfully.");
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            String oldPassword = requestBody.get("oldPassword");
            String newPassword = requestBody.get("newPassword");
            Encoder encoder = Encoder.getInstance();
            userService.changeUserPassword(email, encoder.encode(oldPassword), encoder.encode(newPassword));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("New password updated successfully.");
    }

    @PostMapping("/subscribeNewsletter")
    public ResponseEntity<Object> subscribeNewsletter(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            userService.subscribeNewsletter(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("User subscribed to the newsletter.");
    }

    @PostMapping("/readTimeBySection")
    public ResponseEntity<Object> updateReadTime(@RequestBody Map<String, String> requestBody) {
        try {
            String email = requestBody.get("email");
            String section = requestBody.get("section");
            String timeString = requestBody.get("time");
            BigDecimal timeBigDecimal = new BigDecimal(timeString);
            userService.updateLectureTimeForUser(email, section, timeBigDecimal);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Read time updated.");
    }

    @GetMapping("/stats")
    public ResponseEntity<UserStats> getStatistics(
            @RequestParam(name = "user", required = true) String userEmail)
    {
        UserStats userStats = userService.getReaderStats(userEmail);
        return ResponseEntity.ok(userStats);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Object> deleteAccount(
            @RequestParam(name = "userId", required = true) Long userId)
    {
        userService.deleteUserAccount(userId);
        return ResponseEntity.ok().body("Account deleted successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUser();
    }
}
