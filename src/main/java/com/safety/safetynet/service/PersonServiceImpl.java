package com.safety.safetynet.service;

import com.safety.safetynet.dto.*;
import com.safety.safetynet.model.*;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Person service.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static PersonRepository repository;
    private static MedicalRecordRepository medicalRecordRepository;
    private static FireStationRepository fireStationRepository;

    /**
     * Instantiates a new Person service.
     *
     * @param repository              the repository
     * @param medicalRecordRepository the medical record repository
     * @param fireStationRepository   the fire station repository
     */
    public PersonServiceImpl(final PersonRepository repository, final MedicalRecordRepository medicalRecordRepository, final FireStationRepository fireStationRepository){
        this.repository = repository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Person insert(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(int id) {
        Optional<Person> person = this.findById(id);
        person.ifPresent(p -> repository.delete(p));
    }

    @Override
    public Person update(int id, Person person) {
       Optional<Person> p1 = this.findById(id);

       if(p1.isPresent()){
           Person personToUpdate = p1.get();
           personToUpdate.setAddress(person.getAddress());
           personToUpdate.setCity(person.getCity());
           personToUpdate.setEmail(person.getEmail());
           personToUpdate.setPhone(person.getPhone());
           personToUpdate.setZip(person.getZip());
           return repository.save(personToUpdate);
       } else {
           return null;
       }
    }

    @Override
    public void deleteByName(String firstName, String lastName) {
        Optional<Person> p1 = repository.findByFirstNameAndLastName(firstName, lastName);
        p1.ifPresent(p -> repository.delete(p));
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> findAllByAdressIn (List<String> addresses) {
        return repository.findAllByAddressIn(addresses);
    }

    public FireStationCoverage createPersonInfoToStationNumber(List<Person> personList) {
        FireStationCoverage fireStationCoverage = new FireStationCoverage();
            List<PersonInfos> result = new ArrayList<>();

        for(Person person : personList) {
            PersonInfos personInfos = new PersonInfos();
            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate now = LocalDate.now();
            int age = Period.between(birthdate,now).getYears();
            personInfos.setFirstName(person.getFirstName());
            personInfos.setLastName(person.getLastName());
            personInfos.setAddress(person.getAddress());
            personInfos.setPhone(person.getPhone());
            result.add(personInfos);
            System.out.print("age de " + person + " est de " + age);
        if(age < 18) {
            fireStationCoverage.setChild(fireStationCoverage.getChild() + 1);
        } else {
            fireStationCoverage.setAdult(fireStationCoverage.getAdult() + 1);
        }
        }
        fireStationCoverage.setPersonInfosList(result);

        return fireStationCoverage;
    }

    public ChildAlert findChildAlert(String address) {
        List<Person> personList = repository.findAllByAddress(address);
        ChildAlert result = new ChildAlert();
        List<Child> children = new ArrayList<>();
        List<Person> adult = new ArrayList<>();
        for(Person person : personList) {
            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            LocalDate now = LocalDate.now();
            int age = Period.between(birthdate,now).getYears();

            if(age > 18) {
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

    public CommunityEmail createCommunityEmail(String city) {
        List<Person> personList = repository.findAllByCity(city);
        CommunityEmail result = new CommunityEmail();
        List<String> email = new ArrayList<>();

        for(Person p : personList) {
            email.add(p.getEmail());
        }
        result.setEmail(email);
        return result;
    }

    public List<Fire> createFire(String address) {
        List<Person> personList = repository.findAllByAddress(address);
        List<Fire> resultList = new ArrayList<Fire>();

        for(Person p : personList) {
            Fire result = new Fire();
            FireStation fireStation = fireStationRepository.findFireStationByAddress(address);
            Optional<MedicalRecords> medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());

            LocalDate birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(p.getFirstName(), p.getLastName());
            LocalDate now = LocalDate.now();
            int age = Period.between(birthdate,now).getYears();

            result.setFirstName(p.getFirstName());
            result.setLastName(p.getLastName());
            result.setPhone(p.getPhone());
            result.setAge(age);
            result.setStationNumber(fireStation.getStation());

            if(medicalRecord.isPresent()) {
                result.setMedications(medicalRecord.get().getMedications());
                result.setAllergies(medicalRecord.get().getAllergies());
            }
            resultList.add(result);
        }
        return resultList;
    }

    public List<Person> insertAll(List<Person> personList) {
        return repository.saveAll(personList);
    }
}
