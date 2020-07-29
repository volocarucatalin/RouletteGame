package com.game.roullet.entity;

import com.game.roullet.util.RoomStatus;

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


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private List<Registration> registrations = new ArrayList<>();

    @Column
    @Enumerated(EnumType.STRING)
    private RoomStatus status = RoomStatus.OPEN;

    @OneToMany
    @JoinTable(name = "registration", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {
            @JoinColumn(name = "player_id")})
    private List<Player> players = new ArrayList<>();


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

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

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
