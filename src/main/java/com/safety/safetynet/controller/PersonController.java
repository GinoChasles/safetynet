package com.safety.safetynet.controller;

import com.safety.safetynet.model.ChildAlert;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> result = personService.findAll();
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable(value = "id") long id) {
        Optional<Person> result = personService.findById(id);
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.insert(person));
    }

    @PutMapping("/person/{id]")
    public ResponseEntity<Person> update(@PathVariable(value = "id") long id, @RequestBody Person person) {
        Person person1 = personService.update(id, person);
        if(person1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(person1);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> delete(@PathVariable(value = "id") long id) {
        Optional<Person> person = personService.findById(id);
        if(person.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            personService.delete(person.get().getId());
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping(value = "/childAlert", params = "address")
    public ResponseEntity<ChildAlert> getChildAlert(@RequestParam(value = "address") String address) {
        ChildAlert result = personService.findChildAlert(address);
        if(result == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }
//    @DeleteMapping("/person/firstname/{firstname}/lastname/{lastname}")
//    public ResponseEntity<Person> deleteByFirstnameAndLastName(@PathVariable(value = "firstname")
//                     String firstName, @PathVariable(value = "lastname") String lastname) {
//
//    }
}
