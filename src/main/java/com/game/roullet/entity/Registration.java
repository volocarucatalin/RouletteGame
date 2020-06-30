package com.game.roullet.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "registrations")
public class Registration implements Serializable {

    @Id
    @Column
    private Integer playerId;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "registration")
    private Player player;

    @Column
    private String role;

    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "room")
    private Room room;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
