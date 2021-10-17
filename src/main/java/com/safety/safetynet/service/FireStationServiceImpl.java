package com.safety.safetynet.service;

import com.safety.safetynet.model.*;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
public class FireStationServiceImpl implements FireStationService {
    FireStationRepository repository;
    PersonServiceImpl personServiceImpl;
    PersonRepository personRepository;
    MedicalRecordRepository medicalRecordRepository;

    public FireStationServiceImpl(FireStationRepository repository, PersonServiceImpl personServiceImpl, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.repository = repository;
        this.personServiceImpl = personServiceImpl;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public Optional<FireStation> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public FireStation insert(FireStation fireStation) {
        return repository.save(fireStation);
    }

    @Override
    public void delete(long id) {
        Optional<FireStation> fireStation = this.findById(id);
        fireStation.ifPresent(station -> repository.delete(station));
    }

    @Override
    public FireStation update(long id, FireStation fireStation) {
        Optional<FireStation> fireStation1 = this.findById(id);
        if (fireStation1.isPresent()) {
            FireStation fireStationToUpdate = fireStation1.get();
            fireStationToUpdate.setStation(fireStation.getStation());

            return repository.save(fireStationToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteByName(String firstName, String lastName) {

    }

    @Override
    public List<FireStation> findAll() {
        return repository.findAll();
    }

    @Override
    //get persons by stationnumber
    public FireStationCoverage findAllByFireStationNumber(long stationNumber) {
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
    public PhoneAlert createPhoneAlert(long stationNumber) {
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
    public List<Map<String, List<Flood>>> createFlood(List<Long> stationNumberList) {
        List<Map<String, List<Flood>>> result2 = null;
        Map<String, List<Flood>> result = new HashMap<>();

        List<Flood> floodList = new ArrayList<Flood>();
        for (long el : stationNumberList) {
            FireStation fireStation = repository.findFireStationByStation(el);
            List<Person> personList = personRepository.findAllByAddress(fireStation.getAddress());

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

                if (medicalRecord.isPresent()) {
                    flood.setMedications(medicalRecord.get().getMedications());
                    flood.setAllergies(medicalRecord.get().getAllergies());
                }
                floodList.add(flood);
            }

                result.put(fireStation.getAddress(), floodList);
        result2.add(result);
        }
        return result2;
    }
}
