package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;

@Entity
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

//    @ElementCollection
    String medication;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    MedicalRecords medicalRecords;

    public Medications(String medication) {
        this.medication = medication;
    }

    public Medications(Long id, String medication) {
        this.id = id;
        this.medication = medication;
    }
    public Medications() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
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
        Medications that = (Medications) o;
        return Objects.equals(id, that.id) && medication.equals(that.medication) && Objects.equals(medicalRecords, that.medicalRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medication, medicalRecords);
    }

    @Override
    public String toString() {
        return "Medications{" +
                "medication=" + medication +
                '}';
    }
}
