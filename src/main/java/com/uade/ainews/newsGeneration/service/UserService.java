package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    public static final int INTEREST_INCREMENT_VALUE = 5;
    public static final int MAX_INTEREST_VALUE = 100;
    @Autowired
    private UserRepository userRepository;

    public void addInterest(String userEmail, String section) {
        getUserInterestInSection(getSpecificUser(userEmail), section);
    }

    public void registerUser(String email, String password) {
        // Check if the mail already exists
        if (userRepository.findByEmail(email) == null) {
            // Encoding the password before storing it in the database
            userRepository.save(User.builder().email(email).password(password).build());
        } else {
            throw new RuntimeException("The email is already registered!");
        }
    }

    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public User getSpecificUser(String email) {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found: " + email));
    }

    private void getUserInterestInSection(User reader, String section) {
        switch (section){
            case "POLITICS":
                Integer politicsInterest = reader.getPoliticsInterest();
                politicsInterest = politicsInterest + INTEREST_INCREMENT_VALUE;
                if(politicsInterest > MAX_INTEREST_VALUE) {
                    politicsInterest = MAX_INTEREST_VALUE;
                }
                reader.setPoliticsInterest(politicsInterest);
                break;
            case "ECONOMY":
                Integer economyInterest = reader.getEconomyInterest();
                economyInterest = economyInterest + INTEREST_INCREMENT_VALUE;
                if(economyInterest > MAX_INTEREST_VALUE) {
                    economyInterest = MAX_INTEREST_VALUE;
                }
                reader.setEconomyInterest(economyInterest);
                break;
            case "SPORTS":
                Integer sportsInterest = reader.getSportsInterest();
                sportsInterest = sportsInterest + INTEREST_INCREMENT_VALUE;
                if(sportsInterest > MAX_INTEREST_VALUE) {
                    sportsInterest = MAX_INTEREST_VALUE;
                }
                reader.setSportsInterest(sportsInterest);
                break;
            case "SOCIAL":
                Integer socialInterest = reader.getSocialInterest();
                socialInterest = socialInterest + INTEREST_INCREMENT_VALUE;
                if(socialInterest > MAX_INTEREST_VALUE) {
                    socialInterest = MAX_INTEREST_VALUE;
                }
                reader.setSocialInterest(socialInterest);
                break;
            case "INTERNATIONAL":
                Integer internationalInterest = reader.getInternationalInterest();
                internationalInterest = internationalInterest + INTEREST_INCREMENT_VALUE;
                if(internationalInterest > MAX_INTEREST_VALUE) {
                    internationalInterest = MAX_INTEREST_VALUE;
                }
                reader.setInternationalInterest(internationalInterest);
                break;
            case "POLICE":
                Integer policeInterest = reader.getPoliceInterest();
                policeInterest = policeInterest + INTEREST_INCREMENT_VALUE;
                if(policeInterest > MAX_INTEREST_VALUE) {
                    policeInterest = MAX_INTEREST_VALUE;
                }
                reader.setPoliceInterest(policeInterest);
                break;
            default:
                break;
        }
        userRepository.save(reader);
    }
}