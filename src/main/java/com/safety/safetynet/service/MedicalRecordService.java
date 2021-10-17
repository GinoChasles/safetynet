package com.safety.safetynet.service;

import com.safety.safetynet.model.MedicalRecords;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    Optional<MedicalRecords> findById(long id);
    MedicalRecords insert(MedicalRecords medicalRecords);
    void delete(long id);
    MedicalRecords update(long id, MedicalRecords medicalRecords);
    void deleteByName(String firstName, String lastName);
    List<MedicalRecords> findAll();
}
