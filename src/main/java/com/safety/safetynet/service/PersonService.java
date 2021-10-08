package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.FireStationCoverage;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.model.PersonInfos;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements CrudService<Person> {
    @Autowired
    PersonRepository repository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public Optional<Person> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Person insert(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(long id) {
        Optional<Person> person = this.findById(id);
        person.ifPresent(p -> repository.delete(p));
    }

    @Override
    public Person update(long id, Person person) {
       Optional<Person> p1 = this.findById(id);
       if(p1.isPresent()){
           Person personToUpdate = p1.get();
           personToUpdate.setAddress(person.getAddress());
           personToUpdate.setCity(person.getCity());
           personToUpdate.setEmail(person.getEmail());
//           personToUpdate.setMedicalRecord(person.getMedicalRecord());
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
            DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
            Date birthdate = medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName());
            Date now = new Date();
            int d1 = Integer.parseInt(formatter.format(birthdate));
            int d2 = Integer.parseInt(formatter.format(now));
            int age = (d2 - d1) / 10000;
            personInfos.setFirstName(person.getFirstName());
            personInfos.setLastName(person.getLastName());
            personInfos.setAddress(person.getAddress());
            personInfos.setPhone(person.getPhone());
            result.add(personInfos);
        if(age < 18) {
            fireStationCoverage.setChild(fireStationCoverage.getChild()+1);
        } else {
            fireStationCoverage.setAdult(fireStationCoverage.getAdult()+1);
        }
        }
        fireStationCoverage.setPersonInfosList(result);

        return fireStationCoverage;

    }
}
