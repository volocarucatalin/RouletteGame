package com.game.roullet.response;

public class GetRoomResponse {
    private String roomStatus;
    private int numberOfPlayers;
    private int lastResult;
    private Long lastResultTime;

    public GetRoomResponse() {
    }

    public GetRoomResponse(String roomStatus, int numberOfPlayers, int lastResult, Long lastResultTime) {
        this.roomStatus = roomStatus;
        this.numberOfPlayers = numberOfPlayers;
        this.lastResult = lastResult;
        this.lastResultTime = lastResultTime;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
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

    public Long getLastResultTime() {
        return lastResultTime;
    }

    public void setLastResultTime(Long lastResultTime) {
        this.lastResultTime = lastResultTime;
    }
}
