package com.safety.safetynet.service;

import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PhoneAlert;
import com.safety.safetynet.model.*;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import org.springframework.stereotype.Service;

/**
 * The type Fire station service.
 */
@Service
public class FireStationServiceImpl implements FireStationService {
  /**
   * FireStationRepo.
   */
  private final FireStationRepository repository;
  /**
   * PersonServiceImpl.
   */
  private final PersonServiceImpl personServiceImpl;
  /**
   * PersonRepo.
   */
  private final PersonRepository personRepository;
  /**
   * MedicalRecordRepo.
   */
  private final MedicalRecordRepository medicalRecordRepository;

  /**
   * Instantiates a new Fire station service.
   *
   * @param repository1              the repository
   * @param personServiceImpl1       the person service
   * @param personRepository1        the person repository
   * @param medicalRecordRepository1 the medical record repository
   */
  public FireStationServiceImpl(final FireStationRepository repository1,
                                final PersonServiceImpl personServiceImpl1,
                                final PersonRepository personRepository1,
                                final MedicalRecordRepository
                                    medicalRecordRepository1) {
    this.repository = repository1;
    this.personServiceImpl = personServiceImpl1;
    this.personRepository = personRepository1;
    this.medicalRecordRepository = medicalRecordRepository1;
  }

  /**
   * FindById.
   *
   * @param id1 id
   * @return Optional of FireStation
   */
  @Override
  public Optional<FireStation> findById(final int id1) {
    return repository.findById(id1);
  }

  /**
   * Insert.
   *
   * @param fireStation1 firstation
   * @return Firestation
   */
  @Override
  public FireStation insert(final FireStation fireStation1) {
    return repository.save(fireStation1);
  }

  /**
   * Delete.
   *
   * @param id1 id
   */
  @Override
  public void delete(final int id1) {
    Optional<FireStation> fireStation = this.findById(id1);
    fireStation.ifPresent(station -> repository.delete(station));
  }

  /**
   * Update.
   *
   * @param id1 id
   * @param fireStation1 firestation
   * @return firestation
   */
  @Override
  public FireStation update(final int id1, final FireStation fireStation1) {
    Optional<FireStation> fireStation2 = this.findById(id1);
    if (fireStation2.isPresent()) {
      FireStation fireStationToUpdate = fireStation2.get();
      fireStationToUpdate.setStation(fireStation1.getStation());
      fireStationToUpdate.setAddress(fireStation1.getAddress());

      return repository.save(fireStationToUpdate);
    } else {
      return null;
    }
  }

  /**
   * FindAll.
   *
   * @return List of firestation
   */
  @Override
  public List<FireStation> findAll() {
    return repository.findAll();
  }

  /**
   * Find coverage of person by station number.
   *
   * @param stationNumber1 stationumber
   * @return firestationcoverage
   */
  @Override
  //get persons by stationnumber
  public FireStationCoverage findAllByFireStationNumber(
      final int stationNumber1) {
    //on récupère les stations dont le stationNumber correspond
    List<FireStation> fireStations = repository
        .findAllByStation(stationNumber1);
    List<String> addresses = new ArrayList<>();
    FireStationCoverage result;
    //on ajout les adresses des stations dans une liste
    for (int i = 0; i < fireStations.size(); i++) {
      addresses.add(fireStations.get(i).getAddress());
    }
    //on récupère les personnes dont l'adresse
    // correspond aux adresses des firestations
    List<Person> personList = personServiceImpl
        .findAllByAdressIn(addresses);
    // pour chaque personne on vient créer les infos d'une personne

    result = personServiceImpl.createPersonInfoToStationNumber(personList);
    return result;
  }

  /**
   * PhoneAlert.
   *
   * @param stationNumber1 station number
   * @return list of phone number
   */
  @Override
  public PhoneAlert createPhoneAlert(final int stationNumber1) {
    List<FireStation> fireStationList = repository
        .findAllByStation(stationNumber1);
    List<String> phoneList = new ArrayList<>();
    List<String> addresses = new ArrayList<>();
    PhoneAlert result = new PhoneAlert();
    for (FireStation f : fireStationList) {
      addresses.add(f.getAddress());
    }
    List<Person> personList = personServiceImpl
        .findAllByAdressIn(addresses);
    for (Person p : personList) {
      phoneList.add(p.getPhone());
    }
    result.setPhoneList(phoneList);
    return result;
  }

  /**
   * Create flood.
   *
   * @param stationNumberList1 list
   * @return List of map of string and list of flood
   */
  @Override
  public List<Map<String, List<Flood>>> createFlood(
      final List<Integer> stationNumberList1) {
    List<Map<String, List<Flood>>> result2 = new ArrayList<>();
    Map<String, List<Flood>> result = new HashMap<>();
    List<Flood> floodList = new ArrayList<>();

    for (int el : stationNumberList1) {
      List<FireStation> fireStation = repository
          .findFireStationByStation(el);
      for (FireStation f : fireStation) {
        List<Person> personList = personRepository
            .findAllByAddress(f.getAddress());

        for (Person p : personList) {
          Flood flood = new Flood();

          LocalDate birthdate = medicalRecordRepository
              .findBirthDateByFirstNameAndLastName(
                  p.getFirstName(), p.getLastName());
          LocalDate now = LocalDate.now();
          int age = Period.between(birthdate, now).getYears();

          flood.setFirstName(p.getFirstName());
          flood.setLastName(p.getLastName());
          flood.setPhone(p.getPhone());
          flood.setAge(age);

          Optional<MedicalRecords> medicalRecord =
              medicalRecordRepository
                  .findByFirstNameAndLastName(
                      p.getFirstName(), p.getLastName());
          if (medicalRecord.isPresent()) {
            flood.setAllergies(medicalRecord.get().getAllergies());
            flood.setMedications(
                medicalRecord.get().getMedications());
          }
          floodList.add(flood);
        }

        result.put(f.getAddress(), floodList);
      }
    }
    result2.add(result);
    return result2;
  }
}
