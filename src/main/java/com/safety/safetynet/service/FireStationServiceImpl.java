package com.safety.safetynet.service;

import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PhoneAlert;
import com.safety.safetynet.model.*;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * The type Fire station service.
 */
@Service
public class FireStationServiceImpl implements FireStationService {
    private final FireStationRepository repository;
    private final PersonServiceImpl personServiceImpl;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    /**
     * Instantiates a new Fire station service.
     *
     * @param repository              the repository
     * @param personServiceImpl       the person service
     * @param personRepository        the person repository
     * @param medicalRecordRepository the medical record repository
     */
    public FireStationServiceImpl(FireStationRepository repository, PersonServiceImpl personServiceImpl, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.repository = repository;
        this.personServiceImpl = personServiceImpl;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Optional<FireStation> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public FireStation insert(FireStation fireStation) {
        return repository.save(fireStation);
    }

    @Override
    public void delete(int id) {
        Optional<FireStation> fireStation = this.findById(id);
        fireStation.ifPresent(station -> repository.delete(station));
    }

    @Override
    public FireStation update(int id, FireStation fireStation) {
        Optional<FireStation> fireStation1 = this.findById(id);
        if (fireStation1.isPresent()) {
            FireStation fireStationToUpdate = fireStation1.get();
            fireStationToUpdate.setStation(fireStation.getStation());
            fireStationToUpdate.setAddress(fireStation.getAddress());

            return repository.save(fireStationToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<FireStation> findAll() {
        return repository.findAll();
    }

    @Override
    //get persons by stationnumber
    public FireStationCoverage findAllByFireStationNumber(int stationNumber) {
        //on récupère les stations dont le stationNumber correspond
        List<FireStation> fireStations = repository.findAllByStation(stationNumber);
        List<String> addresses = new ArrayList<>();
        FireStationCoverage result = new FireStationCoverage();
        //on ajout les adresses des stations dans une liste
        for (int i = 0; i < fireStations.size(); i++) {
            addresses.add(fireStations.get(i).getAddress());
        }
        //on récupère les personnes dont l'adresse correspond aux adresses des firestations
        List<Person> personList = personServiceImpl.findAllByAdressIn(addresses);
        // pour chaque personne on vient créer les infos d'une personne

        result = personServiceImpl.createPersonInfoToStationNumber(personList);
        return result;
    }

    @Override
    public PhoneAlert createPhoneAlert(int stationNumber) {
        List<FireStation> fireStationList = repository.findAllByStation(stationNumber);
        List<String> phoneList = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        PhoneAlert result = new PhoneAlert();
        for (FireStation f : fireStationList) {
            addresses.add(f.getAddress());
        }
        List<Person> personList = personServiceImpl.findAllByAdressIn(addresses);
        for (Person p : personList) {
            phoneList.add(p.getPhone());
        }
        result.setPhoneList(phoneList);
        return result;
    }

    @Override
    public List<Map<String, List<Flood>>> createFlood(List<Integer> stationNumberList) {
        List<Map<String, List<Flood>>> result2 = new ArrayList<>();
        Map<String, List<Flood>> result = new HashMap<>();
        List<Flood> floodList = new ArrayList<>();

        for (int el : stationNumberList) {
            List<FireStation> fireStation = repository.findFireStationByStation(el);
            for (FireStation f : fireStation) {
                List<Person> personList = personRepository.findAllByAddress(f.getAddress());

                for (Person p : personList) {
                    Flood flood = new Flood();
                    Optional<MedicalRecords> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

                    LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                    LocalDate now = LocalDate.now();
                    int age = Period.between(birthdate, now).getYears();

                    flood.setFirstName(p.getFirstName());
                    flood.setLastName(p.getLastName());
                    flood.setPhone(p.getPhone());
                    flood.setAge(age);

                    if(medicalRecord.isPresent()) {
                        flood.setAllergies(medicalRecord.get().getAllergies());
                        flood.setMedications(medicalRecord.get().getMedications());
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
