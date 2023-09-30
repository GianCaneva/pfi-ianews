package com.uade.ainews.newsGeneration.service;

import com.uade.ainews.newsGeneration.dto.User;
import com.uade.ainews.newsGeneration.repository.UserRepository;
import com.uade.ainews.newsGeneration.security.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //////////////////////////////// CONSTANTS ////////////////////////////////
    public static final int MAX_INTEREST_VALUE = 100;
    ///////////////////////////////////////////////////////////////////////////

    public void addInterest(String userEmail, String section, Integer amountOfExtraInterest) {
        getUserInterestInSection(getSpecificUser(userEmail), section, amountOfExtraInterest);
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

    public void subscribeNewsletter(String email) {
        User specificUser = getSpecificUser(email);
        specificUser.setNewsletter(true);
        userRepository.save(specificUser);
    }

    public void updateLectureTimeForUser(String email, String section, BigDecimal lastReadTime) {
        User reader = getSpecificUser(email);

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

    public void changeUserPassword(String email, String oldPassword, String newPassword) {
        User specificUser = getSpecificUser(email);
        Encoder encoder = Encoder.getInstance();
        if (encoder.matches(oldPassword, specificUser.getPassword())) {
            specificUser.setPassword(newPassword);
            userRepository.save(specificUser);
        } else {
            throw new RuntimeException("Invalid Password. Please, retry");
        }
    }
}