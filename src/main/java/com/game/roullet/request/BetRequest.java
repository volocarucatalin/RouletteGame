package com.game.roullet.request;

import java.io.Serializable;

public class BetRequest implements Serializable {

    private String betType;
    private int betAmount;
    private byte number;

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

    public byte getNumber() {
        return number;
    }

    public void setNumber(byte number) {
        this.number = number;
    }
}
