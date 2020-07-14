package com.game.roullet.request;

import com.game.roullet.entity.Bet;

import java.io.Serializable;

public class BetRequest implements Serializable {

    private String betType;
    private int betAmount;
    private int playerId;
    private int betTypeValue;

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getBetTypeValue() {
        return betTypeValue;
    }

    public void setBetTypeValue(int betTypeValue) {
        this.betTypeValue = betTypeValue;
    }
}
