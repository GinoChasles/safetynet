package com.safety.safetynet.controller;

import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.service.MedicalRecordServiceImpl;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Medical record controller.
 */
@RestController
public class MedicalRecordController {

  /**
   * Logger.
   */
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * MedicalRecordServiceImpl.
   */
  private final MedicalRecordServiceImpl medicalRecordServiceImpl;

  /**
   * Instantiates a new Medical record controller.
   *
   * @param medicalRecordServiceImpl1 the medical record service
   */
  public MedicalRecordController(
      final MedicalRecordServiceImpl medicalRecordServiceImpl1) {
    this.medicalRecordServiceImpl = medicalRecordServiceImpl1;
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping("/medicalrecord")
  public ResponseEntity<List<MedicalRecords>> findAll() {
    logger.info("Recherche de la liste des medicalRecords");
    List<MedicalRecords> result = medicalRecordServiceImpl.findAll();
    if (result.isEmpty()) {
      logger.error("Not found.");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Liste des medicalrecords : " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @GetMapping("/medicalrecord/{id}")
  public ResponseEntity<Optional<MedicalRecords>> findById(
      @PathVariable(value = "id") final int id) {
    logger.info("recherche d'un medicalRecord par l'id: " + id);
    Optional<MedicalRecords> result = medicalRecordServiceImpl.findById(id);
    if (result.isEmpty()) {
      logger.error("not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("Medicalrecord avec l'id " + id + " : " + result);
      return ResponseEntity.ok().body(result);
    }
  }

  /**
   * Add medical record response entity.
   *
   * @param medicalRecords the medical records
   * @return the response entity
   */
  @PostMapping("/medicalrecord")
  public ResponseEntity<MedicalRecords> addMedicalRecord(
      @RequestBody final MedicalRecords medicalRecords) {
    logger.info("Cr??ation d'un medicalrecord.");
    return ResponseEntity.ok(medicalRecordServiceImpl
        .insert(medicalRecords));
  }

  /**
   * Update response entity.
   *
   * @param id             the id
   * @param medicalRecords the medical records
   * @return the response entity
   */
  @PutMapping("medicalrecord/{id}")
  public ResponseEntity<MedicalRecords> update(
      @PathVariable(value = "id") final int id,
      final MedicalRecords medicalRecords) {
    logger.info("Modification du medicalrecord n??" + id);
    MedicalRecords medicalRecords1 =
        medicalRecordServiceImpl.update(id, medicalRecords);
    if (medicalRecords1 == null) {
      logger.error("Not found");
      return ResponseEntity.notFound().build();
    } else {
      logger.info("MedicalRecord n??" + id + " a ??t?? mis ?? jour.");
      return ResponseEntity.ok().body(medicalRecords1);
    }
  }

  /**
   * Delete response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("medicalrecord/{id}")
  public ResponseEntity<MedicalRecords> delete(
      @PathVariable(value = "id") final int id) {
    logger.info("Demande de suppression du medicalrecord n??" + id);
    Optional<MedicalRecords> medicalRecord =
        medicalRecordServiceImpl.findById(id);
    if (medicalRecord.isEmpty()) {
      logger.error("Not found.");
      return ResponseEntity.notFound().build();
    } else {
      medicalRecordServiceImpl.delete(medicalRecord.get().getId());
      logger.info("MedicalRecord n??" + id + " a ??t?? supprim??");
      return ResponseEntity.accepted().build();
    }
  }
}
