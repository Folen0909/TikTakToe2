package com.turkalj.tiktaktoe.service;

import com.turkalj.tiktaktoe.dto.PlayerDTO;
import com.turkalj.tiktaktoe.model.Player;
import com.turkalj.tiktaktoe.repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Page<PlayerDTO> findAllPlayersDTO(Pageable pageable) {
        return playerRepository.findAll(pageable).map(Player::convertToPlayerDTO);
    }

    @Override
    public PlayerDTO findPlayerDTOById(Long id) {
        return playerRepository.findPlayerById(id).convertToPlayerDTO();
    }

    @Override
    public Player findPlayerById(Long id) {
        return playerRepository.findPlayerById(id);
    }

    @Override
    public Player findPlayerByName(String name) {
        return playerRepository.findPlayerByName(name);
    }

    @Override
    public Player createPlayer(Player player) {
        player.setWins(0L);
        player.setLosses(0L);
        player.setDraws(0L);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player updatePlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    @Override
    public void deletePlayerById(Long id) {

        playerRepository.deletePlayerById(id);
    }
}
