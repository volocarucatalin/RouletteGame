package com.game.roullet.response;

import java.io.Serializable;

public class ResponsePlayer implements Serializable {

    private Integer id;

    public ResponsePlayer(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
