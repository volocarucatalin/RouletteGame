package com.game.roullet.entity;

import javax.persistence.*;


@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @MapsId
    Registration registration;


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
}
