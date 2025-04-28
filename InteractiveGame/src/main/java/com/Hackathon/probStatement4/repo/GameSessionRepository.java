package com.Hackathon.probStatement4.repo;

import com.Hackathon.probStatement4.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GameSessionRepository extends JpaRepository<GameSession, String> {

}

