package com.Hackathon.probStatement4.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameSession {
    @Id
    private String sessionId;

    @Lob
    private String chatHistory;

    private LocalDateTime lastUpdated = LocalDateTime.now();

    // Getters & setters
}


