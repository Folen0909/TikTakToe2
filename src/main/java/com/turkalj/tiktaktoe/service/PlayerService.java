package com.turkalj.tiktaktoe.service;

import com.turkalj.tiktaktoe.dto.PlayerDTO;
import com.turkalj.tiktaktoe.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Page<PlayerDTO> findAllPlayersDTO(Pageable pageable);

    PlayerDTO findPlayerDTOById(Long id);
    Player findPlayerById(Long id);

    Player findPlayerByName(String name);

    Player createPlayer(Player player);


    Player updatePlayer(Player player);

    void deletePlayerById(Long id);


}
