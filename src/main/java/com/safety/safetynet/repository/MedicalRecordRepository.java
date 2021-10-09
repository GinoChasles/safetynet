package com.safety.safetynet.repository;

import com.safety.safetynet.model.MedicalRecord;
import com.safety.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    Optional<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select r.birthdate from MedicalRecord r where r.firstName = :firstName and r.lastName = :lastName")
    LocalDate findBirthDateByFirstNameAndLastName(String firstName, String lastName);

}
