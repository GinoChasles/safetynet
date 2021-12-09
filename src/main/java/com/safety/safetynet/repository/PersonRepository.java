package com.safety.safetynet.repository;

import com.safety.safetynet.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Person repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

  /**
   * Find by first name and last name optional.
   *
   * @param firstName the first name
   * @param lastName  the last name
   * @return the optional
   */
  Optional<Person> findByFirstNameAndLastName(String firstName,
                                              String lastName);


  /**
   * Find all by address in list.
   *
   * @param addresses the addresses
   * @return the list
   */
  List<Person> findAllByAddressIn(List<String> addresses);

  /**
   * Find all by address list.
   *
   * @param address the address
   * @return the list
   */
  @Query("select p from Person p where p.address = :address")
  List<Person> findAllByAddress(String address);

  /**
   * Find all by city list.
   *
   * @param city the city
   * @return the list
   */
  List<Person> findAllByCity(String city);

  /**
   * Find all by last name list.
   *
   * @param lastName the last name
   * @return the list
   */
  List<Person> findAllByLastName(String lastName);

}
