package com.safety.safetynet.data;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Person;
import java.util.List;
import org.springframework.stereotype.Component;


/**
 * The type Data object.
 */
@Component

public class DataObject {
  /**
   * List of persons.
   */
  private List<Person> persons;
  /**
   * List of firestations.
   */
  private List<FireStation> firestations;
  /**
   * List of medicalRecords.
   */
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
   * @param persons1 the persons
   */
  public void setPersons(final List<Person> persons1) {
    this.persons = persons1;
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
   * @param firestations1 the firestations
   */
  public void setFirestations(final List<FireStation> firestations1) {
    this.firestations = firestations1;
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
   * @param medicalrecords1 the medicalrecords
   */
  public void setMedicalrecords(final List<MedicalRecords> medicalrecords1) {
    this.medicalrecords = medicalrecords1;
  }
}
