package com.game.roullet.entity;

import com.game.roullet.util.BetStatus;

import javax.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bet_generator")
    @SequenceGenerator(name = "bet_generator", sequenceName = "bet_seq", allocationSize = 50)
    @Column
    int id;

    @Column
    private String betType;

    @Column
    private int betAmount;

    @Column
    private int betTypeValue;

    @Column
    @Enumerated(EnumType.STRING)
    private BetStatus status = BetStatus.OPEN;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_id")
    Player player;

    public Bet() {
    }

    public Bet(int id, String betType, int betAmount, int betTypeValue, Player player) {
        this.id = id;
        this.betType = betType;
        this.betAmount = betAmount;
        this.betTypeValue = betTypeValue;
        this.player = player;
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


    public int getBetTypeValue() {
        return betTypeValue;
    }

    public void setBetTypeValue(int betTypeValue) {
        this.betTypeValue = betTypeValue;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }
}
