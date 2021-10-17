package com.safety.safetynet.model;

import java.util.List;
import java.util.Set;

public class Fire {
    private long stationNumber;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private List<Medications> medications;
    private Set<Allergies> allergies;

    public Fire() {
    }

    public Fire(long stationNumber, String firstName, String lastName, String phone, int age, List<Medications> medications, Set<Allergies> allergies) {
        this.stationNumber = stationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public long getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(long stationNumber) {
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
}
