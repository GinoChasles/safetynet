package com.safety.safetynet.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
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

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Allergie> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergie> allergies) {
        this.allergies = allergies;
    }
}
