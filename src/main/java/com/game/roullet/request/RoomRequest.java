package com.game.roullet.request;
import java.io.Serializable;

public class RoomRequest implements Serializable {

    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
