package com.game.roullet.response;

import java.io.Serializable;

public class JoinResponse implements Serializable {
    private boolean roomStatus;

    public boolean getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }
}
