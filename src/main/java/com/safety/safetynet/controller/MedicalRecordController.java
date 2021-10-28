package com.safety.safetynet.controller;

import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.service.MedicalRecordServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalRecordController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MedicalRecordServiceImpl medicalRecordServiceImpl;

    public MedicalRecordController(MedicalRecordServiceImpl medicalRecordServiceImpl) {
        this.medicalRecordServiceImpl = medicalRecordServiceImpl;
    }

    @GetMapping("/medicalrecord")
    public ResponseEntity<List<MedicalRecords>> findAll() {
        logger.info("Recherche de la liste des medicalRecords");
        List<MedicalRecords> result = medicalRecordServiceImpl.findAll();
        if(result.isEmpty()) {
            logger.error("Not found.");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Liste des medicalrecords : " + result);
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/medicalrecord/{id}")
    public ResponseEntity<Optional<MedicalRecords>> findById(@PathVariable(value = "id") long id) {
        logger.info("recherche d'un medicalRecord par l'id: " + id);
        Optional<MedicalRecords> result = medicalRecordServiceImpl.findById(id);
        if(result.isEmpty()) {
            logger.error("not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Medicalrecord avec l'id " + id + " : " + result);
            return ResponseEntity.ok().body(result);
        }
    }
    @PostMapping("/medicalrecord")
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        logger.info("Création d'un medicalrecord.");
        return ResponseEntity.ok(medicalRecordServiceImpl.insert(medicalRecords));
    }

    @PutMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> update(@PathVariable(value = "id") long id, MedicalRecords medicalRecords) {
        logger.info("Modification du medicalrecord n°"+id);
        MedicalRecords medicalRecords1 = medicalRecordServiceImpl.update(id, medicalRecords);
        if(medicalRecords1 == null) {
            logger.error("Not found");
            return ResponseEntity.notFound().build();
        } else {
            logger.info("MedicalRecord n°" + id + " a été mis à jour.");
            return ResponseEntity.ok().body(medicalRecords1);
        }
    }

    @DeleteMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> delete(@PathVariable(value = "id") long id) {
        logger.info("Demande de suppression du medicalrecord n°" + id);
        Optional<MedicalRecords> medicalRecord = medicalRecordServiceImpl.findById(id);
        if(medicalRecord.isEmpty()) {
            logger.error("Not found.");
            return ResponseEntity.notFound().build();
        } else {
            medicalRecordServiceImpl.delete(medicalRecord.get().getId());
            logger.info("MedicalRecord n°" + id + "a été supprimé");
            return ResponseEntity.accepted().build();
        }
    }
}
