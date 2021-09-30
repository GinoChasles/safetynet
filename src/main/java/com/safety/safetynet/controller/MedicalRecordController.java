package com.safety.safetynet.controller;

import com.safety.safetynet.model.MedicalRecord;
import com.safety.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/medicalrecord")
    public ResponseEntity<List<MedicalRecord>> findAll() {
        List<MedicalRecord> result = medicalRecordService.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping("/medicalrecord")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.insert(medicalRecord));
    }

    @PutMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecord> update(@PathVariable(value = "id") long id, MedicalRecord medicalRecord) {
        MedicalRecord medicalRecord1 = medicalRecordService.update(id, medicalRecord);
        if(medicalRecord1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(medicalRecord1);
        }
    }

    @DeleteMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecord> delete(@PathVariable(value = "id") long id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordService.findById(id);
        if(medicalRecord.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            medicalRecordService.delete(medicalRecord.get().getId());
            return ResponseEntity.accepted().build();
        }
    }
}
