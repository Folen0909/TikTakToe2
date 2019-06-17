package com.turkalj.tiktaktoe.service;

import com.turkalj.tiktaktoe.dto.GameDTO;

public interface GameService {
    GameDTO findGameById(Long id);
    GameDTO createGame(String first, String second) throws Exception;
    GameDTO playGameMove(Long id, Long row, Long column) throws Exception;
    void deleteGameById(Long id);
}
