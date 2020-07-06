package com.game.roullet.request;

import com.game.roullet.entity.Bet;

import java.io.Serializable;

public class BetRequest implements Serializable {
    private Bet bet;

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
