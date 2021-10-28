package com.safety.safetynet.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Allergies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String allergies;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = MedicalRecords.class, mappedBy = "allergies")
    @JsonBackReference
    Set<MedicalRecords> medicalRecords;

    public Allergies() {
    }

    public Allergies(String allergies) {
        this.allergies = allergies;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public Set<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Allergies{" +
                "id=" + id +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
