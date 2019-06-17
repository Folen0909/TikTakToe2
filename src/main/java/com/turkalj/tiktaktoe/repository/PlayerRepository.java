package com.turkalj.tiktaktoe.repository;

import com.turkalj.tiktaktoe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findPlayerById(Long id);
    Player findPlayerByName(String name);
    void deletePlayerById(Long id);
}

