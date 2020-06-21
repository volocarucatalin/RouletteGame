package com.game.roullet.entity;

import javax.persistence.*;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue
    @Column
    private Integer room;

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }
}
