package com.safety.safetynet.repository;

import com.safety.safetynet.model.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergiesRepository extends JpaRepository<Allergies, Long> {
}
