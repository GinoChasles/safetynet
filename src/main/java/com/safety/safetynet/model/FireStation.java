package com.safety.safetynet.model;


import javax.persistence.*;

@Entity
public class FireStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String address;
    private int station;

    public FireStation(String address, int station) {
        this.address = address;
        this.station = station;
    }

    public FireStation(int id, String address, int station) {
        this.id = id;
        this.address = address;
        this.station = station;
    }

    public FireStation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }
}
