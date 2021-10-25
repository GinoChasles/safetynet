package com.safety.safetynet.repository;

import com.safety.safetynet.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
    List<FireStation> findAllByStation(long stationNumber);
    List<FireStation> findAllByAddress(String address);
    FireStation findFireStationByAddress(String address);
    List<FireStation> findFireStationByStation(long stationNumber);
}
