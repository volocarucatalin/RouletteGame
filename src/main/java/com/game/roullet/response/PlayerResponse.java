package com.game.roullet.response;

import java.io.Serializable;

public class PlayerResponse implements Serializable {

    private Integer id;

    public PlayerResponse(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
