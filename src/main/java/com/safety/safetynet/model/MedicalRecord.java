package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthdate;

    @ElementCollection
    private List<String> medicationstest;
//    @OneToMany
//    private List<Medication> medications;
//
//    @OneToMany
//    private List<Allergie> allergies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

//    public List<Medication> getMedications() {
//        return medications;
//    }
//
//    public void setMedications(List<Medication> medications) {
//        this.medications = medications;
//    }
//
//    public List<Allergie> getAllergies() {
//        return allergies;
//    }
//
//    public void setAllergies(List<Allergie> allergies) {
//        this.allergies = allergies;
//    }
}
