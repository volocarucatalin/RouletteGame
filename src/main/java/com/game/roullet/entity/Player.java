package com.game.roullet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_generator")
    @SequenceGenerator(name = "player_generator", sequenceName = "player_seq", allocationSize = 50)
    @Column
    private Integer id;

    @Column
    private Integer balance = 100;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "player")
    private Registration registration;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "player")
    private List<Bet> bet = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public void addAmount(int amount) {
        balance = balance + amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Bet> getBet() {
        return bet;
    }

    public void setBet(List<Bet> bet) {
        this.bet = bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId().equals(player.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
