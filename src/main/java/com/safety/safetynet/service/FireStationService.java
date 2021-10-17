package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.FireStationCoverage;
import com.safety.safetynet.model.Flood;
import com.safety.safetynet.model.PhoneAlert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FireStationService {
    Optional<FireStation> findById(long id);
    FireStation insert(FireStation fireStation);
    void delete(long id);
    FireStation update(long id, FireStation fireStation);
    void deleteByName(String firstName, String lastName);
    List<FireStation> findAll();
    FireStationCoverage findAllByFireStationNumber(long stationNumber);
    PhoneAlert createPhoneAlert(long stationNumber);
    List<Map<String, List<Flood>>> createFlood(List<Long> stationNumberList);


}
