package com.safety.safetynet.dto;

/**
 * The type Child.
 */
public class Child {
  /**
   * FirstName.
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
   * Instantiates a new Child.
   *
   * @param firstName1 the first name
   * @param lastName1  the last name
   * @param age1       the age
   */
  public Child(final String firstName1,
               final String lastName1, final int age1) {
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.age = age1;
  }

  /**
   * Instantiates a new Child.
   */
  public Child() {
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
   * Tostring method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "Child{" + "firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\''
        + ", age=" + age + '}';
  }
}
