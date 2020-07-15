package com.game.roullet.response;

import java.io.Serializable;
import java.util.Objects;

public class RoomResponse implements Serializable {

    public RoomResponse() {

    }

    public RoomResponse(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomResponse)) return false;
        RoomResponse that = (RoomResponse) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
