package com.game.roullet.entity;

import javax.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue
    @Column
    int id;

    @Column
    private String betType;

    @Column
    private int betAmount;

    @Column
    private int playerId;

    @Column
    private int betTypeValue;

    public Bet() {
    }


    public Bet(String betType, int betAmount, int playerId, int betTypeValue) {
        this.betType = betType;
        this.betAmount = betAmount;
        this.playerId = playerId;
        this.betTypeValue = betTypeValue;
    }

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
