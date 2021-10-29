package com.safety.safetynet.dto;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.Medications;

import java.util.List;
import java.util.Set;

public class Fire {
    private int stationNumber;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<Medications> medications;
    private Set<Allergies> allergies;

    public Fire() {
    }

    public Fire(int stationNumber, String firstName, String lastName, String phone, int age, List<Medications> medications, Set<Allergies> allergies) {
        this.stationNumber = stationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return "Fire{" +
                "stationNumber=" + stationNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", medications=" + medications.toString() +
                ", allergies=" + allergies.toString() +
                '}';
    }
}
