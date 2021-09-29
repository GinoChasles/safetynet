package com.safety.safetynet.repository;

import com.safety.safetynet.model.MedicalRecord;
import com.safety.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    public Optional<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);
}
