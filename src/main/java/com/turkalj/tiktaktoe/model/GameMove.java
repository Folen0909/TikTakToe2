package com.turkalj.tiktaktoe.model;

import com.turkalj.tiktaktoe.dto.GameMoveDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameMove extends BaseEntity {

    private Long rowNumber;
    private Long columnNumber;
    private char symbol;
    private Long gameId;
    private Long lastPlayerId;

    public GameMoveDTO convertToGameMoveDTO() {
        GameMoveDTO gameMoveDTO = new GameMoveDTO();
        gameMoveDTO.setId(getId());
        gameMoveDTO.setRowNumber(rowNumber);
        gameMoveDTO.setColumnNumber(columnNumber);
        gameMoveDTO.setGameId(gameId);
        gameMoveDTO.setLastPlayerId(lastPlayerId);
        gameMoveDTO.setSymbol(symbol);
        return gameMoveDTO;
    }

}
