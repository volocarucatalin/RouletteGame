package com.game.roullet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_seq", allocationSize = 50)
    @Column
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "room")
    private List<Registration> registrations = new ArrayList<>();

   // private List<Bet> bets = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    /*public List<Bet> getBets() {
        return bets;
    }

    public void addBet(Bet bet) {
        this.bets.add(bet);
    }

    public void removeAllBets() {
        this.bets = new ArrayList<>();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getId().equals(room.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
