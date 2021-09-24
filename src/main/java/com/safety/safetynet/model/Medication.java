package com.safety.safetynet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medication {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private int dosing;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDosing() {
        return dosing;
    }

    public void setDosing(int dosing) {
        this.dosing = dosing;
    }
}
