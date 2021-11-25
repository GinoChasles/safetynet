package com.safety.safetynet.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * The type Allergies.
 */
@Entity
public class Allergies {

  /**
   * The id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  /**
   * The Allergies.
   */
  private String allergies;

  /**
   * The Medical records.
   */
  @ManyToMany(fetch = FetchType.LAZY,
      targetEntity = MedicalRecords.class, mappedBy = "allergies")
  @JsonBackReference
  private Set<MedicalRecords> medicalRecords;


  /**
   * Instantiates a new Allergies.
   */
  public Allergies() {
  }

  /**
   * Instantiates a new Allergies.
   *
   * @param allergie the allergies
   */
  public Allergies(final String allergie) {
    this.allergies = allergie;
  }

  /**
   * Gets allergies.
   *
   * @return the allergies
   */
  public String getAllergies() {
    return allergies;
  }

  /**
   * Sets allergies.
   *
   * @param allergie the allergies
   */
  public void setAllergies(final String allergie) {
    this.allergies = allergie;
  }

  /**
   * Gets medical records.
   *
   * @return the medical records
   */
  public Set<MedicalRecords> getMedicalRecords() {
    return medicalRecords;
  }

  /**
   * Sets medical records.
   *
   * @param medicalRecord the medical records
   */
  public void setMedicalRecords(final Set<MedicalRecords> medicalRecord) {
    this.medicalRecords = medicalRecord;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param ids the id
   */
  public void setId(final int ids) {
    this.id = ids;
  }


  /**
   * Method toString.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Allergies{"
        + "id=" + id
        + ", allergies='" + allergies + '\''
        + '}';
  }
}
