package com.safety.safetynet.controller;

import com.safety.safetynet.dto.ChildAlert;
import com.safety.safetynet.dto.CommunityEmail;
import com.safety.safetynet.dto.Fire;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PersonServiceImpl personServiceImpl;

    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> findAll() {
        logger.info("Recherche de la liste des personnes");
        List<Person> result = personServiceImpl.findAll();
        if(result.isEmpty()){
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Listes des personnes: " + result);
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<Person>> findById(@PathVariable(value = "id") long id) {
        logger.info("Recherche d'une personne par l'id: " + id);
        Optional<Person> result = personServiceImpl.findById(id);
        if(result.isEmpty()) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Personne trouvé:" + result);
            return ResponseEntity.ok().body(result);
        }
    }
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        logger.info("Création d'une personne");
        return ResponseEntity.ok(personServiceImpl.insert(person));
    }

    @PutMapping("/person/{id]")
    public ResponseEntity<Person> update(@PathVariable(value = "id") long id, @RequestBody Person person) {
        logger.info("modification de la personne à l'id " + id);
        Person person1 = personServiceImpl.update(id, person);
        if(person1 == null) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Personne avec id:" + id + " a été mis a jour: " + person1);
            return ResponseEntity.ok().body(person1);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> delete(@PathVariable(value = "id") long id) {
        logger.info("Demande de suppression de la personne à l'id " + id);
        Optional<Person> person = personServiceImpl.findById(id);
        if(person.isEmpty()) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {

            personServiceImpl.delete(person.get().getId());
            logger.info("Personne supprimée");
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping(value = "/childAlert", params = "address")
    public ResponseEntity<ChildAlert> getChildAlert(@RequestParam(value = "address") String address) {
        logger.info("recherche des enfants habitant à l'addresse: " + address);
        ChildAlert result = personServiceImpl.findChildAlert(address);
        if(result != null) {
            logger.info("Liste d'enfant habitant à l'adresse " + address + " : " + result);
            return ResponseEntity.ok().body(result);
        } else {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "communityEmail", params = "city")
    public ResponseEntity<CommunityEmail> getCommunityEmail(@RequestParam("city") String city) {
        logger.info("Recherche de la liste d'email des personnes habitant dans la ville: " + city);
        CommunityEmail result = personServiceImpl.createCommunityEmail(city);
        if(result == null) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Liste d'email des personnes habitant dans la ville de " + city + " : " + result);
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(value = "fire", params = "address")
    public ResponseEntity<List<Fire>> getFire(@RequestParam("address") String address) {
        logger.info("Recherche des personnes habitants à l'adresse: " + address);
        List<Fire> result = personServiceImpl.createFire(address);
        if(result.isEmpty()) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Liste des personnes habitant à l'adresse: " + address + ": " + result);
            return ResponseEntity.ok(result);
        }
    }
}
