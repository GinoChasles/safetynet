package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Allergies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    String allergies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    MedicalRecords medicalRecords;

    public Allergies() {
    }

    public Allergies(String allergies) {
        this.allergies = allergies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public MedicalRecords getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(MedicalRecords medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allergies allergies1 = (Allergies) o;
        return Objects.equals(id, allergies1.id) && Objects.equals(allergies, allergies1.allergies) && Objects.equals(medicalRecords, allergies1.medicalRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, allergies, medicalRecords);
    }

    @Override
    public String toString() {
        return "Allergies{" +
                "id=" + id +
                ", allergies='" + allergies + '\'' +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
