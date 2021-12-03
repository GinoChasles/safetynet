package com.safety.safetynet.controller;

import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PhoneAlert;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.service.FireStationServiceImpl;
import com.safety.safetynet.service.PersonServiceImpl;
import java.util.List;
import java.util.Map;
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
 * The type Fire station controller.
 */
@RestController
public class FireStationController {
  /**
   * FireStationServiceImpl.
   */
  private final FireStationServiceImpl fireStationServiceImpl;
  /**
   * PersonServiceImpl.
   */
  private final PersonServiceImpl personServiceImpl;
  /**
   * The Logger.
   */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * Instantiates a new Fire station controller.
   *
   * @param fireStationServiceImpl1 the fire station service
   * @param personServiceImpl1      the person service
   */

  public FireStationController(
          final FireStationServiceImpl fireStationServiceImpl1,
          final PersonServiceImpl personServiceImpl1) {
    this.fireStationServiceImpl = fireStationServiceImpl1;
    this.personServiceImpl = personServiceImpl1;
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping(value = "/firestation")
  public ResponseEntity<List<FireStation>> findAll() {
    logger.info("Recherche de la liste des fireStations.");
    List<FireStation> result = fireStationServiceImpl.findAll();
    if (result.isEmpty()) {
      logger.error("Not found.");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des casernes: " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @GetMapping("/firestation/{id}")
  public ResponseEntity<Optional<FireStation>>
      findById(@PathVariable(value = "id") final int id) {
    logger.info("recherche de la caserne avec l'id: " + id);
    Optional<FireStation> result = fireStationServiceImpl.findById(id);
    if (result.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("caserne trouvée: " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Add fire station response entity.
   *
   * @param fireStation the fire station
   * @return the response entity
   */
  @PostMapping(value = "/firestation")
  public ResponseEntity<FireStation> addFireStation(
          @RequestBody final FireStation fireStation) {
    logger.info("Création d'une fireStation");
    return ResponseEntity.ok(fireStationServiceImpl.insert(fireStation));
  }

  /**
   * Update response entity.
   *
   * @param id          the id
   * @param fireStation the fire station
   * @return the response entity
   */
  @PutMapping(value = "/firestation/{id}")
  public ResponseEntity<FireStation>
      update(@PathVariable(value = "id") final int id,
         @RequestBody final FireStation fireStation) {
    logger.info("Mise à jour d'une fireStation lancée.");
    FireStation fireStation1 = fireStationServiceImpl.update(
            id, fireStation);
    if (fireStation1 == null) {
      logger.error("Pas de fireStation correspondant à l'id :" + id);
      return ResponseEntity.notFound().build();
    } else {
      logger.info("FireStation mis à jour");
      return ResponseEntity.ok().body(fireStation1);
    }
  }

  /**
   * Delete response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping(value = "/firestation/{id}")
  public ResponseEntity<FireStation> delete(
          @PathVariable(value = "id") final int id) {
    logger.info("Suppression en cours de la firestation: " + id);
    Optional<FireStation> fireStation = fireStationServiceImpl.findById(id);
    if (fireStation.isEmpty()) {
      logger.error("Aucune fireStation trouvée");
      return ResponseEntity.notFound().build();
    } else {
      fireStationServiceImpl.delete(fireStation.get().getId());
      logger.info("FireStation " + id + " supprimée.");
      return ResponseEntity.accepted().build();
    }
  }

  /**
   * Gets person by station number.
   *
   * @param id the id
   * @return the person by station number
   */
  @GetMapping(value = "/firestation", params = "stationNumber")
  public ResponseEntity<FireStationCoverage>
      getPersonByStationNumber(
          @RequestParam(value = "stationNumber") final int id) {
    logger.info("Recherche des personnes couvertes par la caserne n° "
            + id);
    FireStationCoverage result =
            fireStationServiceImpl.findAllByFireStationNumber(id);
    if (result.getPersonInfosList().isEmpty()) {
      logger.error("aucune personnes trouvées.");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des personnes couvertes: " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Gets phone alert.
   *
   * @param stationNumber the station number
   * @return the phone alert
   */
  @GetMapping(value = "/phoneAlert", params = "firestation")
  public ResponseEntity<PhoneAlert>
      getPhoneAlert(@RequestParam("firestation") final int stationNumber) {
    logger.info("Recherche des numéros de téléphones desservies "
            + "par la caserne n°" + stationNumber);
    PhoneAlert result =
            fireStationServiceImpl.createPhoneAlert(stationNumber);
    if (result.getPhoneList().isEmpty()) {
      logger.error("Aucun numéro trouvé");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des numéros trouvées à la caserner n°"
              + stationNumber + " : " + result);
      return ResponseEntity.ok(result);
    }
  }

  /**
   * Gets flood.
   *
   * @param stationNumberList the station number list
   * @return the flood
   */
  @GetMapping(value = "flood/stations", params = "stationNumber")
  public ResponseEntity<List<Map<String, List<Flood>>>>
      getFlood(@RequestParam("stationNumber")
               final List<Integer> stationNumberList) {
    logger.info("Recherche des foyers desservis "
            + "par la ou les casernes n°" + stationNumberList);
    List<Map<String, List<Flood>>> result =
            fireStationServiceImpl.createFlood(stationNumberList);
    if (result.isEmpty()) {
      logger.error("Aucun foyer trouvée.");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des foyers desservis: " + result);
      return ResponseEntity.ok(result);
    }
  }
}
