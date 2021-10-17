package com.safety.safetynet.controller;

import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.service.MedicalRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordServiceImpl medicalRecordServiceImpl;

    @GetMapping("/medicalrecord")
    public ResponseEntity<List<MedicalRecords>> findAll() {
        List<MedicalRecords> result = medicalRecordServiceImpl.findAll();
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping("/medicalrecord")
    public ResponseEntity<MedicalRecords> addMedicalRecord(@RequestBody MedicalRecords medicalRecords) {
        return ResponseEntity.ok(medicalRecordServiceImpl.insert(medicalRecords));
    }

    @PutMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> update(@PathVariable(value = "id") long id, MedicalRecords medicalRecords) {
        MedicalRecords medicalRecords1 = medicalRecordServiceImpl.update(id, medicalRecords);
        if(medicalRecords1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(medicalRecords1);
        }
    }

    @DeleteMapping("medicalrecord/{id}")
    public ResponseEntity<MedicalRecords> delete(@PathVariable(value = "id") long id) {
        Optional<MedicalRecords> medicalRecord = medicalRecordServiceImpl.findById(id);
        if(medicalRecord.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            medicalRecordServiceImpl.delete(medicalRecord.get().getId());
            return ResponseEntity.accepted().build();
        }
    }
}
