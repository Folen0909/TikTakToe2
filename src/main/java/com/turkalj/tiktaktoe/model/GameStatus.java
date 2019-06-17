package com.turkalj.tiktaktoe.model;

import com.turkalj.tiktaktoe.dto.GameStatusDTO;
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
public class GameStatus extends BaseEntity{
    private String gameStatus;

    public GameStatusDTO convertToGameStatusDTO() {
        GameStatusDTO gameStatusDTO = new GameStatusDTO();
        gameStatusDTO.setId(getId());
        gameStatusDTO.setGameStatus(gameStatus);
        return gameStatusDTO;
    }
}
