package com.turkalj.tiktaktoe.model;

import com.turkalj.tiktaktoe.dto.PlayerDTO;
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
public class Player extends BaseEntity {

    private String name;
    private Long wins;
    private Long losses;
    private Long draws;

    public Player(String name) {
        this.name = name;
    }

    public PlayerDTO convertToPlayerDTO() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(getId());
        playerDTO.setName(name);
        playerDTO.setWins(wins);
        playerDTO.setLosses(losses);
        playerDTO.setDraws(draws);
        return playerDTO;
    }
}

