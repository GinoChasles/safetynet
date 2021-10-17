package com.safety.safetynet.data;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class DataObject {
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecords> medicalrecords;


    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
