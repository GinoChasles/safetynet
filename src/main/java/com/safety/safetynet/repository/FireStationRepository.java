package com.safety.safetynet.repository;

import com.safety.safetynet.model.MedicalRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Fire station repository.
 */
@Repository
public interface FireStationRepository extends
    JpaRepository<MedicalRecord, Integer> {
  /**
   * Find all by station list.
   *
   * @param stationNumber the station number
   * @return the list
   */
  List<MedicalRecord> findAllByStation(int stationNumber);

  /**
   * Find all by address list.
   *
   * @param address the address
   * @return the list
   */
  List<MedicalRecord> findAllByAddress(String address);

  /**
   * Find fire station by address fire station.
   *
   * @param address the address
   * @return the fire station
   */
  MedicalRecord findFireStationByAddress(String address);

  /**
   * Find fire station by station list.
   *
   * @param stationNumber the station number
   * @return the list
   */
  List<MedicalRecord> findFireStationByStation(int stationNumber);
}
