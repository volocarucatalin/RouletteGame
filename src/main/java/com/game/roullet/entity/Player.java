package com.game.roullet.entity;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    @Column
    private Integer playerId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "player", fetch = FetchType.LAZY)
    Registration registration;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
