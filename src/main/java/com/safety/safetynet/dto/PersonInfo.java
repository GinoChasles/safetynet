package com.safety.safetynet.dto;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.Medications;
import java.util.List;
import java.util.Set;

/**
 * The type Person info.
 */
public class PersonInfo {

  /**
   * FirstName.
   */
  private String firstName;
  /**
   * LastName.
   */
  private String lastName;
  /**
   * Address.
   */
  private String address;
  /**
   * Age.
   */
  private int age;
  /**
   * Email.
   */
  private String email;
  /**
   * List of medications.
   */
  private List<Medications> medications;
  /**
   * Set of Allergies.
   */
  private Set<Allergies> allergies;

  /**
   * Instantiates a new Person info.
   */
  public PersonInfo() {
  }

  /**
   * Instantiates a new Person info.
   *
   * @param firstNameParam   the first name param
   * @param lastNameParam    the last name param
   * @param addressParam     the address param
   * @param ageParam         the age param
   * @param emailParam       the email param
   * @param medicationsParam the medications param
   * @param allergiesParam   the allergies param
   */
  public PersonInfo(final String firstNameParam,
                    final String lastNameParam, final String addressParam,
                    final int ageParam,
                    final String emailParam,
                    final List<Medications> medicationsParam,
                    final Set<Allergies> allergiesParam) {
    firstName = firstNameParam;
    lastName = lastNameParam;
    address = addressParam;
    age = ageParam;
    email = emailParam;
    medications = medicationsParam;
    allergies = allergiesParam;
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
   * @param firstNameParam the first name param
   */
  public void setFirstName(final String firstNameParam) {
    firstName = firstNameParam;
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
   * @param lastNameParam the last name param
   */
  public void setLastName(final String lastNameParam) {
    lastName = lastNameParam;
  }

  /**
   * Gets address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param addressParam the address param
   */
  public void setAddress(final String addressParam) {
    address = addressParam;
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
   * @param ageParam the age param
   */
  public void setAge(final int ageParam) {
    age = ageParam;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param emailParam the email param
   */
  public void setEmail(final String emailParam) {
    email = emailParam;
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
   * @param medicationsParam the medications param
   */
  public void setMedications(final List<Medications> medicationsParam) {
    medications = medicationsParam;
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
   * @param allergiesParam the allergies param
   */
  public void setAllergies(final Set<Allergies> allergiesParam) {
    allergies = allergiesParam;
  }
}
