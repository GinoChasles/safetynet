package com.safety.safetynet.repository;

import com.safety.safetynet.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
}
