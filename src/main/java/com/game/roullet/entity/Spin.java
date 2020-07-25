package com.game.roullet.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "spins")
public class Spin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spin_generator")
    @SequenceGenerator(name = "spin_generator", sequenceName = "spin_seq", allocationSize = 50)
    @Column
    private int id;

    @Column
    private int spinNumber;

    @Column
    private int playerId;

    @Column
    private Date time;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "spin")
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpinNumber() {
        return spinNumber;
    }

    public void setSpinNumber(int spinNumber) {
        this.spinNumber = spinNumber;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
