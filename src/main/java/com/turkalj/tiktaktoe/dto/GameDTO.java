package com.turkalj.tiktaktoe.dto;

public class GameDTO extends BaseEntityDTO {

    private PlayerDTO playerOne;
    private PlayerDTO playerTwo;
    private String winner;
    private GameGridDTO gameGrid;
    private GameStatusDTO gameStatus;

    public PlayerDTO getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(PlayerDTO playerOne) {
        this.playerOne = playerOne;
    }

    public PlayerDTO getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(PlayerDTO playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public GameGridDTO getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(GameGridDTO gameGrid) {
        this.gameGrid = gameGrid;
    }

    public GameStatusDTO getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatusDTO gameStatus) {
        this.gameStatus = gameStatus;
    }
}

