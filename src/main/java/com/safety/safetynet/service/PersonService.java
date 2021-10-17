package com.safety.safetynet.service;

import com.safety.safetynet.model.*;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> findById(long id);
    Person insert(Person person);
    void delete(long id);
    Person update(long id, Person person);
    void deleteByName(String firstName, String lastName);
    List<Person> findAll();
    List<Person> findAllByAdressIn(List<String> address);
    FireStationCoverage createPersonInfoToStationNumber(List<Person> personList);
    ChildAlert findChildAlert(String address);
    CommunityEmail createCommunityEmail(String city);
    List<Fire> createFire(String address);
    List<Person> insertAll(List<Person> personList);
}
