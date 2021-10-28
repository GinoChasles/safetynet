package com.safety.safetynet.repository;

import com.safety.safetynet.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Fire station repository.
 */
@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
    /**
     * Find all by station list.
     *
     * @param stationNumber the station number
     * @return the list
     */
    List<FireStation> findAllByStation(long stationNumber);

    /**
     * Find all by address list.
     *
     * @param address the address
     * @return the list
     */
    List<FireStation> findAllByAddress(String address);

    /**
     * Find fire station by address fire station.
     *
     * @param address the address
     * @return the fire station
     */
    FireStation findFireStationByAddress(String address);

    /**
     * Find fire station by station list.
     *
     * @param stationNumber the station number
     * @return the list
     */
    List<FireStation> findFireStationByStation(long stationNumber);
}
