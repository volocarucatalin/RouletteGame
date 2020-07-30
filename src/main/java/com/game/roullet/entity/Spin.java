package com.game.roullet.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "spins")
public class Spin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spin_generator")
    @SequenceGenerator(name = "spin_generator", sequenceName = "spin_seq")
    @Column
    private int id;

    @Column
    private byte spinNumber;

    @Column
    private int playerId;

    @Column
    private int roomId;

    @Column
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getSpinNumber() {
        return spinNumber;
    }

    public void setSpinNumber(byte spinNumber) {
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
