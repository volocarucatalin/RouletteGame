package com.game.roullet.response;

import com.game.roullet.util.RoomStatus;

import java.util.Date;

public class GetRoomResponse {
    private RoomStatus roomStatus;
    private int numberOfPlayers;
    private int lastResult;
    private Date lastResultTime;

    public GetRoomResponse() {
    }

    public GetRoomResponse(RoomStatus roomStatus, int numberOfPlayers, int lastResult, Date lastResultTime) {
        this.roomStatus = roomStatus;
        this.numberOfPlayers = numberOfPlayers;
        this.lastResult = lastResult;
        this.lastResultTime = lastResultTime;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Date getLastResultTime() {
        return lastResultTime;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getLastResult() {
        return lastResult;
    }

    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    public Date getLastResultTime(Date lastResultTime) {
        return this.lastResultTime;
    }

    public void setLastResultTime(Date lastResultTime) {
        this.lastResultTime = lastResultTime;
    }
}
