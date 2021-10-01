package com.safety.safetynet.model;

import javax.persistence.*;

@Entity
public class FireStation {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    String address;
    int stationNumber;

    @OneToOne
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int station) {
        this.stationNumber = station;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
