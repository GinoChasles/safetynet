package com.safety.safetynet.dto;

/**
 * The type Person infos.
 */
public class PersonInfosForCoverage {
  /**
   * Firstname.
   */
  private String firstName;
  /**
   * Lastname.
   */
  private String lastName;
  /**
   * Address.
   */
  private String address;
  /**
   * Phone.
   */
  private String phone;

  /**
   * Instantiates a new Person infos.
   *
   * @param firstName1 the first name 1
   * @param lastName1  the last name 1
   * @param address1   the address 1
   * @param phone1     the phone 1
   */
  public PersonInfosForCoverage(final String firstName1, final String lastName1,
                                final String address1, final String phone1) {
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.address = address1;
    this.phone = phone1;
  }

  /**
   * Instantiates a new Person infos.
   */
  public PersonInfosForCoverage() {
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
   * @param address1 the address 1
   */
  public void setAddress(final String address1) {
    this.address = address1;
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
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "PersonInfosForCoverage{" + "firstName='" + firstName
        + '\'' + ", lastName='" + lastName + '\'' + ", address='"
        + address + '\'' + ", phone='" + phone + '\'' + '}';
  }
}
