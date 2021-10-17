package com.safety.safetynet.repository;

import com.safety.safetynet.model.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecords, Long> {
    Optional<MedicalRecords> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select r.birthdate from MedicalRecords r where r.firstName = :firstName and r.lastName = :lastName")
    LocalDate findBirthDateByFirstNameAndLastName(String firstName, String lastName);

}
