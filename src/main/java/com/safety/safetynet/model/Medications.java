package com.safety.safetynet.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Medications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    String medication;

    @ManyToMany(targetEntity = MedicalRecords.class, mappedBy = "medications", fetch = FetchType.LAZY)
    @JsonBackReference
    Set<MedicalRecords> medicalRecords;

    public Medications(String medication) {
        this.medication = medication;
    }

    public Medications() {
    }


    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Set<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(Set<MedicalRecords> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Medications{" +
                "medication=" + medication +
                '}';
    }
}
