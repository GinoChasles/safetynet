package com.safety.safetynet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Proxy;

/**
 * The type Medical records.
 */
@Entity
@Proxy(lazy = true)
public class MedicalRecords {
  /**
   * Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  /**
   * FirstName.
   */
  private String firstName;
  /**
   * LastName.
   */
  private String lastName;
  /**
   * Birthdate.
   */
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private LocalDate birthdate;

  /**
   * List of Medications.
   */
  @ManyToMany
  @JoinTable(name = "medicalRecords_medications",
      joinColumns = {@JoinColumn(
          name = "medicalRecords_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(
          name = "medications_id", referencedColumnName = "id")})

  private List<Medications> medications;

  /**
   * Set of allergies.
   */
  @ManyToMany
  @JoinTable(name = "medicalRecords_allergies",
      joinColumns = {@JoinColumn(name = "medicalRecords_id",
          referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(
          name = "allergies_id", referencedColumnName = "id")})
  private Set<Allergies> allergies;

  /**
   * Instantiates a new Medical records.
   *
   * @param id1          the id 1
   * @param firstName1   the first name 1
   * @param lastName1    the last name 1
   * @param birthdate1   the birthdate 1
   * @param medications1 the medications 1
   * @param allergies1   the allergies 1
   */
  public MedicalRecords(final int id1, final String firstName1,
                        final String lastName1, final LocalDate birthdate1,
                        final List<Medications> medications1,
                        final Set<Allergies> allergies1) {
    this.id = id1;
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.birthdate = birthdate1;
    this.medications = medications1;
    this.allergies = allergies1;
  }

  /**
   * Instantiates a new Medical records.
   */
  public MedicalRecords() {

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
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName1 the first name 1
   */
  public void setFirstName(final String firstName1) {
    this.firstName = firstName1;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName1 the last name 1
   */
  public void setLastName(final String lastName1) {
    this.lastName = lastName1;
  }

  /**
   * Gets birthdate.
   *
   * @return the birthdate
   */
  public LocalDate getBirthdate() {
    return birthdate;
  }

  /**
   * Sets birthdate.
   *
   * @param birthdate1 the birthdate 1
   */
  public void setBirthdate(final LocalDate birthdate1) {
    this.birthdate = birthdate1;
  }

  /**
   * Gets medications.
   *
   * @return the medications
   */
  public List<Medications> getMedications() {
    return medications;
  }

  /**
   * Sets medications.
   *
   * @param medications1 the medications 1
   */
  public void setMedications(final List<Medications> medications1) {
    this.medications = medications1;
  }

  /**
   * Gets allergies.
   *
   * @return the allergies
   */
  public Set<Allergies> getAllergies() {
    return allergies;
  }

  /**
   * Sets allergies.
   *
   * @param allergies1 the allergies 1
   */
  public void setAllergies(final Set<Allergies> allergies1) {
    this.allergies = allergies1;
  }

  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "MedicalRecords{" + "id=" + id + ", firstName='"
        + firstName + '\'' + ", lastName='" + lastName
        + '\'' + ", birthdate=" + birthdate + ", medications="
        + medications + ", allergies=" + allergies + '}';
  }
}
