package com.safety.safetynet.controller;

import com.safety.safetynet.model.ChildAlert;
import com.safety.safetynet.model.CommunityEmail;
import com.safety.safetynet.model.Fire;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonServiceImpl personServiceImpl;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> result = personServiceImpl.findAll();
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable(value = "id") long id) {
        Optional<Person> result = personServiceImpl.findById(id);
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personServiceImpl.insert(person));
    }

    @PutMapping("/person/{id]")
    public ResponseEntity<Person> update(@PathVariable(value = "id") long id, @RequestBody Person person) {
        Person person1 = personServiceImpl.update(id, person);
        if(person1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(person1);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> delete(@PathVariable(value = "id") long id) {
        Optional<Person> person = personServiceImpl.findById(id);
        if(person.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            personServiceImpl.delete(person.get().getId());
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping(value = "/childAlert", params = "address")
    public ResponseEntity<ChildAlert> getChildAlert(@RequestParam(value = "address") String address) {
        ChildAlert result = personServiceImpl.findChildAlert(address);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "communityEmail", params = "city")
    public ResponseEntity<CommunityEmail> getCommunityEmail(@RequestParam("city") String city) {
        CommunityEmail result = personServiceImpl.createCommunityEmail(city);
        if(result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(value = "fire", params = "address")
    public ResponseEntity<List<Fire>> getFire(@RequestParam("address") String address) {
        List<Fire> result = personServiceImpl.createFire(address);
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
