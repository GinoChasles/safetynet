package com.safety.safetynet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
   @Id
   @Column(name = "id", nullable = false)
   private Long id;

   private String firstName;
   private String lastName;
   private String address;
   private String city;
   private int zip;
   private String phone;
   private String email;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
