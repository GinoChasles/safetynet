package com.safety.safetynet.repository;

import com.safety.safetynet.model.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Medication repository.
 */
@Repository
public interface MedicationRepository
        extends JpaRepository<Medications, Integer> {
}
