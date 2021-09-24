package com.safety.safetynet.repository;

import com.safety.safetynet.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository  extends JpaRepository<Medication, Long> {
}
