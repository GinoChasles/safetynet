package com.safety.safetynet.data;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Data object.
 */
@Component

public class DataObject {
    private List<Person> persons;
    private List<FireStation> firestations;
    private List<MedicalRecords> medicalrecords;


    /**
     * Gets persons.
     *
     * @return the persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Sets persons.
     *
     * @param persons the persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Gets firestations.
     *
     * @return the firestations
     */
    public List<FireStation> getFirestations() {
        return firestations;
    }

    /**
     * Sets firestations.
     *
     * @param firestations the firestations
     */
    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    /**
     * Gets medicalrecords.
     *
     * @return the medicalrecords
     */
    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    /**
     * Sets medicalrecords.
     *
     * @param medicalrecords the medicalrecords
     */
    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
