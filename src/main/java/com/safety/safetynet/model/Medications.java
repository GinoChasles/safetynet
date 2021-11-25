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
 * The type Medications.
 */
@Entity
public class Medications {


  /**
   * Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  /**
   * Medication.
   */
  private String medication;

  /**
   * MedicalRecords.
   */
  @ManyToMany(targetEntity = MedicalRecords.class,
      mappedBy = "medications", fetch = FetchType.LAZY)
  @JsonBackReference
  private Set<MedicalRecords> medicalRecords;

  /**
   * Instantiates a new Medications.
   *
   * @param medication1 the medication 1
   */
  public Medications(final String medication1) {
    this.medication = medication1;
  }

  /**
   * Instantiates a new Medications.
   */
  public Medications() {
  }


  /**
   * Gets medication.
   *
   * @return the medication
   */
  public String getMedication() {
    return medication;
  }

  /**
   * Sets medication.
   *
   * @param medication1 the medication 1
   */
  public void setMedication(final String medication1) {
    this.medication = medication1;
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
   * @param medicalRecords1 the medical records 1
   */
  public void setMedicalRecords(final Set<MedicalRecords> medicalRecords1) {
    this.medicalRecords = medicalRecords1;
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
   * @param id1 the id 1
   */
  public void setId(final int id1) {
    this.id = id1;
  }


  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Medications{"
        + "medication=" + medication + '}';
  }
}
