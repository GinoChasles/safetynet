package com.safety.safetynet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FireStation {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String address;
    int station;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
