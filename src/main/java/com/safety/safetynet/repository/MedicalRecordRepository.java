package com.safety.safetynet.repository;

import com.safety.safetynet.model.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The interface Medical record repository.
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecords, Long> {
    /**
     * Find by first name and last name optional.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the optional
     */
    Optional<MedicalRecords> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Find birth date by first name and last name local date.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the local date
     */
    @Query("select r.birthdate from MedicalRecords r where r.firstName = :firstName and r.lastName = :lastName")
    LocalDate findBirthDateByFirstNameAndLastName(String firstName, String lastName);

}
