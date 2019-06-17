package com.turkalj.tiktaktoe.model;

import com.turkalj.tiktaktoe.dto.GameDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Player playerOne;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Player playerTwo;

    private String winner;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private GameGrid gameGrid;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private GameStatus gameStatus;

    public GameDTO convertToGameDTO() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(getId());
        gameDTO.setPlayerOne(playerOne.convertToPlayerDTO());
        gameDTO.setPlayerTwo(playerTwo.convertToPlayerDTO());
        gameDTO.setWinner(winner);
        gameDTO.setGameGrid(gameGrid.convertToGameGridDTO());
        gameDTO.setGameStatus(gameStatus.convertToGameStatusDTO());
        return gameDTO;
    }
}
