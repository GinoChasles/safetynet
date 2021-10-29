package com.safety.safetynet.model;


import javax.persistence.*;

@Entity
public class Person {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private int id;

   private String firstName;
   private String lastName;
   private String address;
   private String city;
   private int zip;
   private String phone;
   private String email;


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public int getZip() {
      return zip;
   }

   public void setZip(int zip) {
      this.zip = zip;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "Person{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", address='" + address + '\'' +
              ", city='" + city + '\'' +
              ", zip=" + zip +
              ", phone='" + phone + '\'' +
              ", email='" + email + '\'' +
              '}';
   }
}
