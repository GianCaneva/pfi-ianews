package com.uade.ainews.newsGeneration.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer politicsInterest;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer politicsTime;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer economyInterest;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer economyTime;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer sportsInterest;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer sportsTime;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer socialInterest;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer socialTime;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer internationalInterest;
    @Column(columnDefinition = "INT DEFAULT 100")
    private Integer internationalTime;

}