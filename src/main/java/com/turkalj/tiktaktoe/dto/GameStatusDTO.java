package com.turkalj.tiktaktoe.dto;

public class GameStatusDTO extends BaseEntityDTO {
    private String gameStatus;

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
