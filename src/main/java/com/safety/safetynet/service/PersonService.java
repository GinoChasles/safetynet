package com.safety.safetynet.service;

import com.safety.safetynet.dto.ChildAlert;
import com.safety.safetynet.dto.CommunityEmail;
import com.safety.safetynet.dto.Fire;
import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.PersonInfo;
import com.safety.safetynet.model.Person;
import java.util.List;
import java.util.Optional;

/**
 * The interface Person service.
 */
public interface PersonService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<Person> findById(int id);

  /**
   * Insert person.
   *
   * @param person the person
   * @return the person
   */
  Person insert(Person person);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(int id);

  /**
   * Update person.
   *
   * @param id     the id
   * @param person the person
   * @return the person
   */
  Person update(int id, Person person);

  /**
   * Delete by name.
   *
   * @param firstName the first name
   * @param lastName  the last name
   */
  void deleteByName(String firstName, String lastName);

  /**
   * Find all list.
   *
   * @return the list
   */
  List<Person> findAll();

  /**
   * Find all by adress in list.
   *
   * @param address the address
   * @return the list
   */
  List<Person> findAllByAdressIn(List<String> address);

  /**
   * Create person info to station number fire station coverage.
   *
   * @param personList the person list
   * @return the fire station coverage
   */
  FireStationCoverage createPersonInfoToStationNumber(
      List<Person> personList);

  /**
   * Find child alert child alert.
   *
   * @param address the address
   * @return the child alert
   */
  ChildAlert findChildAlert(String address);

  /**
   * Create community email community email.
   *
   * @param city the city
   * @return the community email
   */
  CommunityEmail createCommunityEmail(String city);

  /**
   * Create fire list.
   *
   * @param address the address
   * @return the list
   */
  List<Fire> createFire(String address);

  /**
   * Insert all list.
   *
   * @param personList the person list
   * @return the list
   */
  List<Person> insertAll(List<Person> personList);

  /**
   * Create person info list.
   *
   * @param firstName the first name
   * @param lastName  the last name
   * @return the list
   */
  List<PersonInfo> createPersonInfoList(String firstName, String lastName);
}
