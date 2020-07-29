package com.game.roullet.response;

import java.io.Serializable;
import java.util.Date;

public class SpinResponse implements Serializable {

    private byte number;

    private int playerId;

    private int roomId;

    private Date time;

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
