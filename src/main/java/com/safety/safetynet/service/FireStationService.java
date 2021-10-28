package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PhoneAlert;

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
    Optional<FireStation> findById(long id);

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
    void delete(long id);

    /**
     * Update fire station.
     *
     * @param id          the id
     * @param fireStation the fire station
     * @return the fire station
     */
    FireStation update(long id, FireStation fireStation);

    /**
     * Delete by name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    void deleteByName(String firstName, String lastName);

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
    FireStationCoverage findAllByFireStationNumber(long stationNumber);

    /**
     * Create phone alert phone alert.
     *
     * @param stationNumber the station number
     * @return the phone alert
     */
    PhoneAlert createPhoneAlert(long stationNumber);

    /**
     * Create flood list.
     *
     * @param stationNumberList the station number list
     * @return the list
     */
    List<Map<String, List<Flood>>> createFlood(List<Long> stationNumberList);


}
