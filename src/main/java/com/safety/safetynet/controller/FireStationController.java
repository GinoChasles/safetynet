package com.safety.safetynet.controller;

import com.safety.safetynet.model.*;
import com.safety.safetynet.service.FireStationServiceImpl;
import com.safety.safetynet.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class FireStationController {
    @Autowired
    private FireStationServiceImpl fireStationServiceImpl;
    @Autowired
    private PersonServiceImpl personServiceImpl;

    @GetMapping(value = "/firestation")
    public ResponseEntity<List<FireStation>> findAll() {
        List<FireStation> result = fireStationServiceImpl.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) {
        return ResponseEntity.ok(fireStationServiceImpl.insert(fireStation));
    }

    @PutMapping(value = "/firestation/{id}")
    public ResponseEntity<FireStation> update(@PathVariable(value = "id") long id, FireStation fireStation) {
        FireStation fireStation1 = fireStationServiceImpl.update(id, fireStation);
        if (fireStation1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(fireStation1);
        }
    }

    @DeleteMapping(value = "/firestation/{id}")
    public ResponseEntity<FireStation> delete(@PathVariable(value = "id") long id) {
        Optional<FireStation> fireStation = fireStationServiceImpl.findById(id);
        if (fireStation.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            fireStationServiceImpl.delete(fireStation.get().getId());
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public ResponseEntity<FireStationCoverage> getPersonByStationNumber(@RequestParam(value = "stationNumber") long id) {
        FireStationCoverage result = fireStationServiceImpl.findAllByFireStationNumber(id);

            if (result == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(result);
            }
        }

    @GetMapping(value = "/phoneAlert", params = "firestation")
    public ResponseEntity<PhoneAlert> getPhoneAlert(@RequestParam("firestation") long stationNumber) {
        PhoneAlert result = fireStationServiceImpl.createPhoneAlert(stationNumber);
        if(result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(value = "flood/stations", params = "stationNumber")
    public ResponseEntity<List<Map<String,List<Flood>>>> getFlood(@RequestParam("stationNumber") List<Long> stationNumberList) {
        List<Map<String, List<Flood>>> result = fireStationServiceImpl.createFlood(stationNumberList);
        if(result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
