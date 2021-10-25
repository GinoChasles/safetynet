package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Allergies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    @JsonIgnore
    private Long id;

    String allergies;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = MedicalRecords.class, mappedBy = "allergies")
//    @JoinColumn(name = "medical_records_id")
//    @JsonIgnore
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

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Allergies allergies1 = (Allergies) o;
//        return Objects.equals(id, allergies1.id) && Objects.equals(allergies, allergies1.allergies) && Objects.equals(medicalRecords, allergies1.medicalRecords);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, allergies, medicalRecords);
//    }

    @Override
    public String toString() {
        return "Allergies{" +

                ", allergies='" + allergies + '\'' +
                ", medicalRecords=" + medicalRecords +
                '}';
    }
}
