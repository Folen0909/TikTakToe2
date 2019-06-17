package com.turkalj.tiktaktoe.dto;

import com.turkalj.tiktaktoe.model.Player;

public class PlayerDTO extends BaseEntityDTO {

    private String name;
    private Long wins;
    private Long losses;
    private Long draws;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWins() {
        return wins;
    }

    public void setWins(Long wins) {
        this.wins = wins;
    }

    public Long getLosses() {
        return losses;
    }

    public void setLosses(Long losses) {
        this.losses = losses;
    }

    public Long getDraws() {
        return draws;
    }

    public void setDraws(Long draws) {
        this.draws = draws;
    }

    public Player convertingFromDTO() {
        Player player = new Player(name);
        player.setWins(wins);
        player.setLosses(losses);
        player.setDraws(draws);
        player.setId(getId());
        return player;
    }
}
