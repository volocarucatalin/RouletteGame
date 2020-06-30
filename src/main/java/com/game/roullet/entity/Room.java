package com.game.roullet.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name="room_generator", sequenceName = "room_seq", allocationSize=50)
    @Column
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room", fetch = FetchType.LAZY)
    private List<Registration> registrations;


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


}
