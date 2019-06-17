package com.turkalj.tiktaktoe.dto;

public class GameMoveDTO extends BaseEntityDTO {
    private Long rowNumber;
    private Long columnNumber;
    private char Symbol;
    private Long gameId;
    private Long lastPlayerId;

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Long getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Long columnNumber) {
        this.columnNumber = columnNumber;
    }

    public char getSymbol() {
        return Symbol;
    }

    public void setSymbol(char setSymbol) {
        this.Symbol = setSymbol;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getLastPlayerId() {
        return lastPlayerId;
    }

    public void setLastPlayerId(Long lastPlayerId) {
        this.lastPlayerId = lastPlayerId;
    }
}
