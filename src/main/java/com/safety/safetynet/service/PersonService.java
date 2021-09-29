package com.safety.safetynet.service;

import com.safety.safetynet.model.Person;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService implements CrudService<Person> {
    @Autowired
    PersonRepository repository;

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
           personToUpdate.setMedicalRecord(person.getMedicalRecord());
           personToUpdate.setPhone(person.getPhone());
           personToUpdate.setZip(person.getZip());

           return repository.save(personToUpdate);
       } else {
           return null;
       }
    }
}
