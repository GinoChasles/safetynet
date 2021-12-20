package com.safety.safetynet.service;

import com.safety.safetynet.dto.Child;
import com.safety.safetynet.dto.ChildAlert;
import com.safety.safetynet.dto.CommunityEmail;
import com.safety.safetynet.dto.Fire;
import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.PersonInfo;
import com.safety.safetynet.dto.PersonInfosForCoverage;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * The type Person service.
 */
@Service
public class PersonServiceImpl implements PersonService {
  /**
   * Repo.
   */
  private PersonRepository repository;
  /**
   * Repo.
   */
  private MedicalRecordRepository medicalRecordRepository;
  /**
   * Repo.
   */
  private FireStationRepository fireStationRepository;

  /**
   * Instantiates a new Person service.
   *
   * @param repository1              the repository
   * @param medicalRecordRepository1 the medical record repository
   * @param fireStationRepository1   the fire station repository
   */
  public PersonServiceImpl(final PersonRepository repository1,
                           final MedicalRecordRepository
                               medicalRecordRepository1,
                           final FireStationRepository fireStationRepository1) {
    this.repository = repository1;
    this.medicalRecordRepository = medicalRecordRepository1;
    this.fireStationRepository = fireStationRepository1;
  }

  /**
   * FindById.
   *
   * @param id the id
   * @return optional person
   */
  @Override
  public Optional<Person> findById(final int id) {
    return repository.findById(id);
  }

  /**
   * Insert.
   *
   * @param person the person
   * @return person
   */
  @Override
  public Person insert(final Person person) {
    return repository.save(person);
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @Override
  public void delete(final int id) {
    Optional<Person> person = this.findById(id);
    person.ifPresent(p -> repository.delete(p));
  }

  /**
   * Update.
   *
   * @param id     the id
   * @param person the person
   * @return person
   */
  @Override
  public Person update(final int id, final Person person) {
    Optional<Person> p1 = this.findById(id);

    if (p1.isPresent()) {
      Person personToUpdate = p1.get();
      personToUpdate.setAddress(person.getAddress());
      personToUpdate.setCity(person.getCity());
      personToUpdate.setEmail(person.getEmail());
      personToUpdate.setPhone(person.getPhone());
      personToUpdate.setZip(person.getZip());
      personToUpdate.setLastName(person.getLastName());
      personToUpdate.setFirstName(person.getFirstName());
      return repository.save(personToUpdate);
    } else {
      return null;
    }
  }

  /**
   * Delete by name.
   *
   * @param firstName the first name
   * @param lastName  the last name
   */
  @Override
  public void deleteByName(final String firstName, final String lastName) {
    Optional<Person> p1 =
        repository.findByFirstNameAndLastName(firstName, lastName);
    p1.ifPresent(p -> repository.delete(p));
  }

  /**
   * FindAll.
   *
   * @return list of person
   */
  @Override
  public List<Person> findAll() {
    return repository.findAll();
  }

  /**
   * Find all by address.
   *
   * @param addresses addresses
   * @return list of person
   */
  @Override
  public List<Person> findAllByAdressIn(final List<String> addresses) {
    return repository.findAllByAddressIn(addresses);
  }

  /**
   * Create firestationcoverage.
   *
   * @param personList the person list
   * @return firestationcoverage
   */
  @Override
  public FireStationCoverage createPersonInfoToStationNumber(
      final List<Person> personList) {
    FireStationCoverage fireStationCoverage = new FireStationCoverage();
    List<PersonInfosForCoverage> result = new ArrayList<>();

    for (Person person : personList) {
      PersonInfosForCoverage personInfosForCoverageLocal =
          new PersonInfosForCoverage();
      LocalDate birthdate =
          medicalRecordRepository.findBirthDateByFirstNameAndLastName(
              person.getFirstName(), person.getLastName());
      LocalDate now = LocalDate.now();
      int age = 0;
      if (birthdate != null) {
        age = Period.between(birthdate, now).getYears();
      }
      personInfosForCoverageLocal.setFirstName(person.getFirstName());
      personInfosForCoverageLocal.setLastName(person.getLastName());
      personInfosForCoverageLocal.setAddress(person.getAddress());
      personInfosForCoverageLocal.setPhone(person.getPhone());
      result.add(personInfosForCoverageLocal);
      if (age < 18) {
        fireStationCoverage.setChild(
            fireStationCoverage.getChild() + 1);
      } else {
        fireStationCoverage.setAdult(
            fireStationCoverage.getAdult() + 1);
      }
    }
    fireStationCoverage.setPersonInfosList(result);

    return fireStationCoverage;
  }

  /**
   * FindChildAlert.
   *
   * @param address the address
   * @return childalert
   */
  @Override
  public ChildAlert findChildAlert(final String address) {
    List<Person> personList = repository.findAllByAddress(address);
    ChildAlert result = new ChildAlert();
    List<Child> children = new ArrayList<>();
    List<Person> adult = new ArrayList<>();
    for (Person person : personList) {
      LocalDate birthdate =
          medicalRecordRepository.findBirthDateByFirstNameAndLastName(
              person.getFirstName(), person.getLastName());
      LocalDate now = LocalDate.now();
      int age = 0;
      if (birthdate != null) {
        age = Period.between(birthdate, now).getYears();
      }
      if (age > 18) {
        adult.add(person);
      } else {
        Child child = new Child();

        child.setFirstName(person.getFirstName());
        child.setLastName(person.getLastName());
        child.setAge(age);
        children.add(child);

      }
    }
    result.setChildren(children);
    result.setFamily(adult);
    return result;
  }

  /**
   * Create communitymail list.
   *
   * @param city the city
   * @return communityMail
   */
  @Override
  public CommunityEmail createCommunityEmail(final String city) {
    List<Person> personList = repository.findAllByCity(city);
    CommunityEmail result = new CommunityEmail();
    List<String> email = new ArrayList<>();

    for (Person p : personList) {
      email.add(p.getEmail());
    }
    Set<String> mySet = new HashSet<String>(email);
    List<String> filterEmail = new ArrayList<String>(mySet);
    result.setEmail(filterEmail);
    return result;
  }

  /**
   * Create fire.
   *
   * @param address the address
   * @return list of fire
   */
  @Override
  public List<Fire> createFire(final String address) {
    List<Person> personList = repository.findAllByAddress(address);
    List<Fire> resultList = new ArrayList<Fire>();

    for (Person p : personList) {
      Fire result = new Fire();

      LocalDate birthdate =
          medicalRecordRepository.findBirthDateByFirstNameAndLastName(
              p.getFirstName(), p.getLastName());
      LocalDate now = LocalDate.now();
      int age = 0;
      if (birthdate != null) {
        age = Period.between(birthdate, now).getYears();
      }
      FireStation fireStation =
          fireStationRepository.findFireStationByAddress(address);
      result.setFirstName(p.getFirstName());
      result.setLastName(p.getLastName());
      result.setPhone(p.getPhone());
      result.setAge(age);
      result.setStationNumber(fireStation.getStation());

      Optional<MedicalRecords> medicalRecord =
          medicalRecordRepository.findByFirstNameAndLastName(
              p.getFirstName(), p.getLastName());
      if (medicalRecord.isPresent()) {
        result.setMedications(medicalRecord.get().getMedications());
        result.setAllergies(medicalRecord.get().getAllergies());
      } else {
        result.setMedications(new ArrayList<>());
        result.setAllergies(new HashSet<>());
      }
      resultList.add(result);
    }
    return resultList;
  }

  /**
   * InsertAll.
   *
   * @param personList the person list
   * @return list of person
   */
  @Override
  public List<Person> insertAll(final List<Person> personList) {
    return repository.saveAll(personList);
  }

  /**
   * Create person info list.
   *
   * @param firstName the first name
   * @param lastName  the last name
   * @return the list
   */
  @Override
  public List<PersonInfo> createPersonInfoList(final String firstName,
                                               final String lastName) {
    List<PersonInfo> result = new ArrayList<>();
    List<Person> personList = repository.findAllByLastName(lastName);


    for (Person p : personList) {
      PersonInfo personInfo = new PersonInfo();
      personInfo.setFirstName(p.getFirstName());
      personInfo.setLastName(p.getLastName());
      personInfo.setAddress(p.getAddress());
      personInfo.setEmail(p.getEmail());

      LocalDate birthdate =
          medicalRecordRepository.findBirthDateByFirstNameAndLastName(
              p.getFirstName(), p.getLastName());
      LocalDate now = LocalDate.now();
      int age = 0;
      if (birthdate != null) {
        age = Period.between(birthdate, now).getYears();
      }

      personInfo.setAge(age);
      Optional<MedicalRecords> medicalRecord =
          medicalRecordRepository.findByFirstNameAndLastName(
              p.getFirstName(), p.getLastName());
      if (medicalRecord.isPresent()) {
        personInfo.setMedications(medicalRecord.get().getMedications());
        personInfo.setAllergies(medicalRecord.get().getAllergies());
      } else {
        personInfo.setMedications(new ArrayList<>());
        personInfo.setAllergies(new HashSet<>());
      }
      result.add(personInfo);
    }
    return result;
  }

}
