package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.dto.UserStats;
import com.uade.ainews.newsGeneration.repository.UserRepository;
import com.uade.ainews.newsGeneration.security.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //////////////////////////////// CONSTANTS ////////////////////////////////
    public static final int MAX_INTEREST_VALUE = 100;
    ///////////////////////////////////////////////////////////////////////////

    public void addInterest(Long userId, String section, Integer amountOfExtraInterest) {
        getUserInterestInSection(getSpecificUserById(userId), section, amountOfExtraInterest);
    }

    public void registerUser(String email, String password) {
        // Check if the mail already exists
        if (userRepository.findByEmail(email) == null) {
            User newUser = new User(email, password);
            // Encoding the password before storing it in the database
            userRepository.save(newUser);
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

    public User getSpecificUserByEmail(String email) {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found: " + email));
    }
    public User getSpecificUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User id found: " + userId));
    }

    private void getUserInterestInSection(User reader, String section, Integer amountOfExtraInterest) {
        switch (section){
            case "POLITICS":
                Integer politicsInterest = reader.getPoliticsInterest();
                politicsInterest = politicsInterest + amountOfExtraInterest;
                if(politicsInterest > MAX_INTEREST_VALUE) {
                    politicsInterest = MAX_INTEREST_VALUE;
                }
                reader.setPoliticsInterest(politicsInterest);
                break;
            case "ECONOMY":
                Integer economyInterest = reader.getEconomyInterest();
                economyInterest = economyInterest + amountOfExtraInterest;
                if(economyInterest > MAX_INTEREST_VALUE) {
                    economyInterest = MAX_INTEREST_VALUE;
                }
                reader.setEconomyInterest(economyInterest);
                break;
            case "SPORTS":
                Integer sportsInterest = reader.getSportsInterest();
                sportsInterest = sportsInterest + amountOfExtraInterest;
                if(sportsInterest > MAX_INTEREST_VALUE) {
                    sportsInterest = MAX_INTEREST_VALUE;
                }
                reader.setSportsInterest(sportsInterest);
                break;
            case "SOCIAL":
                Integer socialInterest = reader.getSocialInterest();
                socialInterest = socialInterest + amountOfExtraInterest;
                if(socialInterest > MAX_INTEREST_VALUE) {
                    socialInterest = MAX_INTEREST_VALUE;
                }
                reader.setSocialInterest(socialInterest);
                break;
            case "INTERNATIONAL":
                Integer internationalInterest = reader.getInternationalInterest();
                internationalInterest = internationalInterest + amountOfExtraInterest;
                if(internationalInterest > MAX_INTEREST_VALUE) {
                    internationalInterest = MAX_INTEREST_VALUE;
                }
                reader.setInternationalInterest(internationalInterest);
                break;
            case "POLICE":
                Integer policeInterest = reader.getPoliceInterest();
                policeInterest = policeInterest + amountOfExtraInterest;
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

    public void subscribeNewsletter(Long userId) {
        User specificUser = getSpecificUserById(userId);
        specificUser.setNewsletter("Y");
        userRepository.save(specificUser);
    }

    public void updateLectureTimeForUser(Long userId, String section, BigDecimal lastReadTime) {
        User reader = getSpecificUserById(userId);

        switch (section){
            case "POLITICS":
                BigDecimal politicsTime = reader.getPoliticsTime();
                politicsTime = (politicsTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setPoliticsTime(politicsTime);
                break;
            case "ECONOMY":
                BigDecimal economyTime = reader.getEconomyTime();
                economyTime = (economyTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setEconomyTime(economyTime);
                break;
            case "SPORTS":
                BigDecimal sportsTime = reader.getSportsTime();
                sportsTime = (sportsTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setSportsTime(sportsTime);
                break;
            case "SOCIAL":
                BigDecimal socialTime = reader.getSocialTime();
                socialTime = (socialTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setSocialTime(socialTime);
                break;
            case "INTERNATIONAL":
                BigDecimal internationalTime = reader.getInternationalTime();
                internationalTime = (internationalTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setInternationalTime(internationalTime);
                break;
            case "POLICE":
                BigDecimal policeTime = reader.getPoliceTime();
                policeTime = (policeTime.add(lastReadTime)).divide(BigDecimal.valueOf(2));
                reader.setPoliceTime(policeTime);
                break;
            default:
                break;
        }
        userRepository.save(reader);
    }

    public void changeUserPassword(Long userId, String oldPassword, String newPassword) {
        User specificUser = getSpecificUserById(userId);
        Encoder encoder = Encoder.getInstance();
        if (encoder.matches(oldPassword, specificUser.getPassword())) {
            specificUser.setPassword(newPassword);
            userRepository.save(specificUser);
        } else {
            throw new RuntimeException("Invalid Username or Password. Please, retry");
        }
    }

    public UserStats getReaderStats(Long userId) {
        User specificUser = getSpecificUserById(userId);
        return UserStats.builder()
                .politicsInterest(specificUser.getPoliticsInterest())
                .politicsTime(specificUser.getPoliticsTime())
                .economyInterest(specificUser.getEconomyInterest())
                .economyTime(specificUser.getEconomyTime())
                .sportsInterest(specificUser.getSportsInterest())
                .sportsTime(specificUser.getSportsTime())
                .socialInterest(specificUser.getSocialInterest())
                .socialTime(specificUser.getSocialTime())
                .internationalInterest(specificUser.getInternationalInterest())
                .internationalTime(specificUser.getInternationalTime())
                .policeInterest(specificUser.getPoliceInterest())
                .politicsTime(specificUser.getPoliticsTime())
                .build();
    }

    public ResponseEntity<String> deleteUserAccount(Long userId) {
        try {
            // Checks if there is a user with that Id on database
            if (userRepository.existsById(userId)) {
                // Delete user by Id
                userRepository.deleteById(userId);
                return ResponseEntity.ok().body("User deleted successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting user");
        }
    }
}