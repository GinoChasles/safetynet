package com.safety.safetynet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private Date birthdate;

    @OneToMany
    private List<Medication> medications;

    @OneToMany
    private List<Allergie> allergies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
