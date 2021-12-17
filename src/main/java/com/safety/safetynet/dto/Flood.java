package com.safety.safetynet.dto;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.Medications;
import java.util.List;
import java.util.Set;

/**
 * The type Flood.
 */
public class Flood {
  /**
   * Firstname.
   */
  private String firstName;
  /**
   * LastName.
   */
  private String lastName;
  /**
   * Age.
   */
  private int age;
  /**
   * Phone.
   */
  private String phone;
  /**
   * List of medications.
   */
  private List<Medications> medications;
  /**
   * Set of allergies.
   */
  private Set<Allergies> allergies;

  /**
   * Instantiates a new Flood.
   *
   * @param firstName1   the first name 1
   * @param lastName1    the last name 1
   * @param age1         the age 1
   * @param phone1       the phone 1
   * @param medications1 the medications 1
   * @param allergies1   the allergies 1
   */
  public Flood(final String firstName1, final String lastName1,
               final int age1, final String phone1,
               final List<Medications> medications1,
               final Set<Allergies> allergies1) {
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.age = age1;
    this.phone = phone1;
    this.medications = medications1;
    this.allergies = allergies1;
  }

  /**
   * Instantiates a new Flood.
   */
  public Flood() {
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
   * Gets age.
   *
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets age.
   *
   * @param age1 the age 1
   */
  public void setAge(final int age1) {
    this.age = age1;
  }

  /**
   * Gets phone.
   *
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets phone.
   *
   * @param phone1 the phone 1
   */
  public void setPhone(final String phone1) {
    this.phone = phone1;
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
   * @return string
   */
  @Override
  public String toString() {
    return "Flood{" + "firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\'' + ", age="
        + age + ", phone='" + phone + '\'';
  }
}
