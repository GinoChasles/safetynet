package com.safety.safetynet.service;

import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PhoneAlert;
import com.safety.safetynet.model.FireStation;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Fire station service.
 */
public interface FireStationService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<FireStation> findById(int id);

  /**
   * Insert fire station.
   *
   * @param fireStation the fire station
   * @return the fire station
   */
  FireStation insert(FireStation fireStation);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(int id);

  /**
   * Update fire station.
   *
   * @param id          the id
   * @param fireStation the fire station
   * @return the fire station
   */
  FireStation update(int id, FireStation fireStation);


  /**
   * Find all list.
   *
   * @return the list
   */
  List<FireStation> findAll();

  /**
   * Find all by fire station number fire station coverage.
   *
   * @param stationNumber the station number
   * @return the fire station coverage
   */
  FireStationCoverage findAllByFireStationNumber(int stationNumber);

  /**
   * Create phone alert phone alert.
   *
   * @param stationNumber the station number
   * @return the phone alert
   */
  PhoneAlert createPhoneAlert(int stationNumber);

  /**
   * Create flood list.
   *
   * @param stationNumberList the station number list
   * @return the list
   */
  List<Map<String, List<Flood>>> createFlood(List<Integer> stationNumberList);


}
