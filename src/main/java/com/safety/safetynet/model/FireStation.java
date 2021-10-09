package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class FireStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String address;
    private long stationNumber;

//    @OneToMany(mappedBy = "address")
//    private List<Person> person;

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

    public long getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(long station) {
        this.stationNumber = station;
    }

//    public List<Person> getPerson() {
//        return person;
//    }
//
//    public void setPerson(List<Person> person) {
//        this.person = person;
//    }
}
