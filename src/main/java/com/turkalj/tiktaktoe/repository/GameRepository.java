package com.turkalj.tiktaktoe.repository;

import com.turkalj.tiktaktoe.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameById(Long id);
    void deleteGameById(Long id);
}
