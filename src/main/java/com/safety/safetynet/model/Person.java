package com.safety.safetynet.model;


import javax.persistence.*;

/**
 * The type Person.
 */
@Entity
public class Person {
  /**
   * Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

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
   * City.
   */
  private String city;
  /**
   * Zip.
   */
  private int zip;
  /**
   * Phone.
   */
  private String phone;
  /**
   * Email.
   */
  private String email;

  /**
   * Instantiates a new Person.
   *
   * @param id1        the id 1
   * @param firstName1 the first name 1
   * @param lastName1  the last name 1
   * @param address1   the address 1
   * @param city1      the city 1
   * @param zip1       the zip 1
   * @param phone1     the phone 1
   * @param email1     the email 1
   */
  public Person(final int id1, final String firstName1,
                final String lastName1, final String address1,
                final String city1, final int zip1,
                final String phone1, final String email1) {
    this.id = id1;
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.address = address1;
    this.city = city1;
    this.zip = zip1;
    this.phone = phone1;
    this.email = email1;
  }

  /**
   * Instantiates a new Person.
   *
   * @param firstName1 the first name 1
   * @param lastName1  the last name 1
   * @param address1   the address 1
   * @param city1      the city 1
   * @param zip1       the zip 1
   * @param phone1     the phone 1
   * @param email1     the email 1
   */
  public Person(final String firstName1,
                final String lastName1,
                final String address1,
                final String city1,
                final int zip1,
                final String phone1,
                final String email1) {
    this.firstName = firstName1;
    this.lastName = lastName1;
    this.address = address1;
    this.city = city1;
    this.zip = zip1;
    this.phone = phone1;
    this.email = email1;
  }

  /**
   * Instantiates a new Person.
   */
  public Person() {
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
   * Gets city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets city.
   *
   * @param city1 the city 1
   */
  public void setCity(final String city1) {
    this.city = city1;
  }

  /**
   * Gets zip.
   *
   * @return the zip
   */
  public int getZip() {
    return zip;
  }

  /**
   * Sets zip.
   *
   * @param zip1 the zip 1
   */
  public void setZip(final int zip1) {
    this.zip = zip1;
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
   * @param email1 the email 1
   */
  public void setEmail(final String email1) {
    this.email = email1;
  }

  /**
   * ToString method.
   *
   * @return String1
   */
  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", firstName='"
        + firstName + '\'' + ", lastName='" + lastName
        + '\'' + ", address='" + address + '\''
        + ", city='" + city + '\'' + ", zip="
        + zip + ", phone='" + phone + '\''
        + ", email='" + email + '\'' + '}';
  }
}
