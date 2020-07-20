package com.game.roullet.response;

import java.io.Serializable;
import java.util.Objects;

public class PlayerResponse implements Serializable {

    private Integer id;

    public PlayerResponse(int id) {
        this.id = id;
    }

    public PlayerResponse() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerResponse)) return false;
        PlayerResponse that = (PlayerResponse) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
