package com.safety.safetynet.dto;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;

import java.util.List;
import java.util.Set;

public class Flood {
    private String firstName;
    private String lastName;
    private int age;
    private String phone;
    private List<Medications> medications;
    private Set<Allergies> allergies;

    public Flood(String firstName, String lastName, int age, String phone, List<Medications> medications, Set<Allergies> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.medications = medications;
        this.allergies = allergies;
    }

    public Flood() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Flood{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", medications=" + medications.toString() +
                ", allergies=" + allergies.toString() +
                '}';
    }
}
