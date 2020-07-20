package com.game.roullet.response;

import java.io.Serializable;

public class JoinResponse implements Serializable {
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
