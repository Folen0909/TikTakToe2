package com.turkalj.tiktaktoe.dto;

import java.util.HashSet;
import java.util.Set;

public class GameGridDTO extends BaseEntityDTO {
    private Set<GameMoveDTO> grid = new HashSet<>();

    public Set<GameMoveDTO> getGrid() {
        return grid;
    }

    public void setGrid(Set<GameMoveDTO> grid) {
        this.grid = grid;
    }
}
