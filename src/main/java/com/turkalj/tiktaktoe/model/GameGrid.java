package com.turkalj.tiktaktoe.model;

import com.turkalj.tiktaktoe.dto.GameGridDTO;
import com.turkalj.tiktaktoe.dto.GameMoveDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameGrid extends BaseEntity {

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<GameMove> gameMoves = new HashSet<>();

    public GameGridDTO convertToGameGridDTO() {
        GameGridDTO gameGridDTO = new GameGridDTO();
        Set<GameMoveDTO> gameMoveDTOS = new HashSet<>();
        for (GameMove gameMove : gameMoves) {
            GameMoveDTO gameMoveDTO = gameMove.convertToGameMoveDTO();
            gameMoveDTOS.add(gameMoveDTO);
        }
        gameGridDTO.setGrid(gameMoveDTOS);
        gameGridDTO.setId(getId());
        return gameGridDTO;
    }

}