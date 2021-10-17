package com.safety.safetynet.controller;

import com.safety.safetynet.model.MedicalRecords;
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
    public ResponseEntity<List<MedicalRecords>> findAll() {
        List<MedicalRecords> result = medicalRecordService.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping("/medicalrecord")
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return ResponseEntity.ok(medicalRecordService.insert(medicalRecords));
    }

    @PutMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> update(@PathVariable(value = "id") long id, MedicalRecords medicalRecords) {
        MedicalRecords medicalRecords1 = medicalRecordService.update(id, medicalRecords);
        if(medicalRecords1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(medicalRecords1);
        }
    }

    @DeleteMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> delete(@PathVariable(value = "id") long id) {
        Optional<MedicalRecords> medicalRecord = medicalRecordService.findById(id);
        if(medicalRecord.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            medicalRecordService.delete(medicalRecord.get().getId());
            return ResponseEntity.accepted().build();
        }
    }
}
