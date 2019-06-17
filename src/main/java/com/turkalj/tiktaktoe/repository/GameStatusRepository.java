package com.turkalj.tiktaktoe.repository;

import com.turkalj.tiktaktoe.model.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatusRepository extends JpaRepository<GameStatus, Long> {

    GameStatus findGameStatusById(Long id);
}
