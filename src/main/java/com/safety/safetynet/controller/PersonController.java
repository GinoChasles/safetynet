package com.safety.safetynet.controller;

import com.safety.safetynet.dto.ChildAlert;
import com.safety.safetynet.dto.CommunityEmail;
import com.safety.safetynet.dto.Fire;
import com.safety.safetynet.dto.PersonInfo;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.PersonServiceImpl;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
public class PersonController {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * PersonServiceImpl.
   */
  private final PersonServiceImpl personServiceImpl;

  /**
   * Instantiates a new Person controller.
   *
   * @param personServiceImpl1 the person service
   */
  public PersonController(final PersonServiceImpl personServiceImpl1) {
    this.personServiceImpl = personServiceImpl1;
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping("/person")
  public ResponseEntity<List<Person>> findAll() {
    logger.info("Recherche de la liste des personnes");
    List<Person> result = personServiceImpl.findAll();
    if (result.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Listes des personnes: " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @GetMapping("/person/{id}")
  public ResponseEntity<Optional<Person>> findById(
      @PathVariable(value = "id") final int id) {
    logger.info("Recherche d'une personne par l'id: " + id);
    Optional<Person> result = personServiceImpl.findById(id);
    if (result.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Personne trouv??:" + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Add person response entity.
   *
   * @param person the person
   * @return the response entity
   */
  @PostMapping("/person")
  public ResponseEntity<Person> addPerson(@RequestBody final Person person) {
    logger.info("Cr??ation d'une personne");
    return ResponseEntity.ok(personServiceImpl.insert(person));
  }

  /**
   * Update response entity.
   *
   * @param id     the id
   * @param person the person
   * @return the response entity
   */
  @PutMapping("/person/{id}")
  public ResponseEntity<Person> update(
      @PathVariable(value = "id") final int id,
      @RequestBody final Person person) {
    logger.info("modification de la personne ?? l'id " + id);
    Person person1 = personServiceImpl.update(id, person);
    if (person1 == null) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info(
          "Personne avec id:" + id + " a ??t?? mis a jour: " + person1);
      return ResponseEntity.ok().body(person1);
    }
  }

  /**
   * Delete response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/person/{id}")
  public ResponseEntity<Person> delete(
      @PathVariable(value = "id") final int id) {
    logger.info("Demande de suppression de la personne ?? l'id " + id);
    Optional<Person> person = personServiceImpl.findById(id);
    if (person.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {

      personServiceImpl.delete(person.get().getId());
      logger.info("Personne supprim??e");
      return ResponseEntity.accepted().build();
    }
  }

  /**
   * Gets child alert.
   *
   * @param address the address
   * @return the child alert
   */
  @GetMapping(value = "/childAlert", params = "address")
  public ResponseEntity<ChildAlert> getChildAlert(
      @RequestParam(value = "address") final String address) {
    ChildAlert result = personServiceImpl.findChildAlert(address);
    logger.info("Liste d'enfant habitant ?? l'adresse "
        + address + " : " + result);
      return ResponseEntity.ok().body(result);

  }

  /**
   * Gets community email.
   *
   * @param city the city
   * @return the community email
   */
  @GetMapping(value = "communityEmail", params = "city")
  public ResponseEntity<CommunityEmail> getCommunityEmail(
      @RequestParam("city") final String city) {
    logger.info(
        "Recherche de la liste d'email "
            + "des personnes habitant dans la ville: "
            + city);
    CommunityEmail result = personServiceImpl.createCommunityEmail(city);
    if (result.getEmail().isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info(
          "Liste d'email des personnes habitant dans la ville de "
              + city + " : " + result);
      return ResponseEntity.ok(result);
    }
  }

  /**
   * Gets fire.
   *
   * @param address the address
   * @return the fire
   */
  @GetMapping(value = "fire", params = "address")
  public ResponseEntity<List<Fire>> getFire(
      @RequestParam("address") final String address) {
    logger.info(
        "Recherche des personnes habitants ?? l'adresse: " + address);
    List<Fire> result = personServiceImpl.createFire(address);
    if (result.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des personnes habitant ?? l'adresse: " + address
          + ": " + result);
      return ResponseEntity.ok(result);
    }
  }

  /**
   * Gets person info.
   *
   * @param firstName the first name
   * @param lastName  the last name
   * @return the person info
   */
  @GetMapping(value = "personInfo", params = {"firstName", "lastName"})
  public ResponseEntity<List<PersonInfo>> getPersonInfo(
      @RequestParam("firstName") final String firstName,
      @RequestParam("lastName") final String lastName) {
    logger.info("Recherche des infos d'une personne");
    List<PersonInfo> result = personServiceImpl
        .createPersonInfoList(firstName, lastName);
    if (result.isEmpty()) {
      logger.error("Not found.");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Infos d'une personne ou de celles portant le m??me nom");
      return ResponseEntity.ok(result);
    }
  }
}
