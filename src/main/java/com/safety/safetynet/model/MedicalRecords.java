package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;

    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate birthdate;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "medicalRecords")
//    @ManyToMany(targetEntity = Medications.class, mappedBy = "medicalRecords")
    @ManyToMany
    @JoinTable(name = "medicalRecords_medications",
            joinColumns = {@JoinColumn(name = "medicalRecords_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "medications_id", referencedColumnName = "id")})
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @Fetch(value = FetchMode.JOIN)
    private List<Medications> medications;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicalRecords")
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @Fetch(value = FetchMode.JOIN)
//    @JsonIgnore
//    @ManyToMany(targetEntity = Allergies.class, mappedBy = "medicalRecords")
    @ManyToMany
//    @JsonManagedReference
    @JoinTable(name = "medicalRecords_allergies",
    joinColumns = {@JoinColumn(name = "medicalRecords_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "allergies_id", referencedColumnName = "id")})
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

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
