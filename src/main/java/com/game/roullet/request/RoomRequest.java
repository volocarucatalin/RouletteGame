package com.game.roullet.request;

import java.io.Serializable;
import java.util.Objects;

public class RoomRequest implements Serializable {

    public RoomRequest() {

    }

    public RoomRequest(int playerId) {
        this.playerId = playerId;
    }

    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomRequest)) return false;
        RoomRequest that = (RoomRequest) o;
        return getPlayerId() == that.getPlayerId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId());
    }
}
