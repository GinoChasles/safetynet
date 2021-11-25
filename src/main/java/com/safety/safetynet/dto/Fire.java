package com.safety.safetynet.dto;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.Medications;
import java.util.List;
import java.util.Set;

/**
 * The type Fire.
 */
public class Fire {
  /**
   * StationNumber.
   */
  private int stationNumber;
  /**
   * FirstName.
   */
  private String firstName;
  /**
   * LastName.
   */
  private String lastName;
  /**
   * Phone.
   */
  private String phone;
  /**
   * Age.
   */
  private int age;
  /**
   * List of medications.
   */
  private List<Medications> medications;
  /**
   * Set of Allergies.
   */
  private Set<Allergies> allergies;

  /**
   * Instantiates a new Fire.
   */
  public Fire() {
  }

  /**
   * Instantiates a new Fire.
   *
   * @param stationNumber1 the station number 1
   * @param firstName1     the first name 1
   * @param lastName1      the last name 1
   * @param phone1         the phone 1
   * @param age1           the age 1
   * @param medications1   the medications 1
   * @param allergies1     the allergies 1
   */
  public Fire(final int stationNumber1, final String firstName1,
              final String lastName1, final String phone1,
              final int age1, final List<Medications> medications1,
              final Set<Allergies> allergies1) {
    this.stationNumber = stationNumber1;
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.phone = phone1;
    this.age = age1;
    this.medications = medications1;
    this.allergies = allergies1;
  }

  /**
   * Gets station number.
   *
   * @return the station number
   */
  public int getStationNumber() {
    return stationNumber;
  }

  /**
   * Sets station number.
   *
   * @param stationNumber1 the station number 1
   */
  public void setStationNumber(final int stationNumber1) {
    this.stationNumber = stationNumber1;
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
    return "Fire{" + "stationNumber=" + stationNumber + ", firstName='"
        + firstName + '\'' + ", lastName='" + lastName
        + '\'' + ", phone='" + phone + '\''
        + ", age=" + age + ", medications=" + medications.toString()
        + ", allergies=" + allergies.toString() + '}';
  }
}
