package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Proxy(lazy = true)
public class MedicalRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate birthdate;


//    @ElementCollection
//    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @Fetch(value = FetchMode.JOIN)
    private List<Medications> medications;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.JOIN)
//    @JsonIgnore
//    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Allergies> allergies;

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Medications> getMedications() {
        return medications;
    }

    public void setMedications(List<Medications> medications) {
        this.medications = medications;
    }

    public Set<Allergies> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Allergies> allergies) {
        this.allergies = allergies;
    }
}
