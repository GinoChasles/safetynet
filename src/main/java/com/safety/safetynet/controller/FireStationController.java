package com.safety.safetynet.controller;

import com.safety.safetynet.model.*;
import com.safety.safetynet.service.FireStationService;
import com.safety.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FireStationController {
    @Autowired
    private FireStationService fireStationService;
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/firestation")
    public ResponseEntity<List<FireStation>> findAll() {
        List<FireStation> result = fireStationService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping(value = "/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStation) {
        return ResponseEntity.ok(fireStationService.insert(fireStation));
    }

    @PutMapping(value = "/firestation/{id}")
    public ResponseEntity<FireStation> update(@PathVariable(value = "id") long id, FireStation fireStation) {
        FireStation fireStation1 = fireStationService.update(id, fireStation);
        if (fireStation1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(fireStation1);
        }
    }

    @DeleteMapping(value = "/firestation/{id}")
    public ResponseEntity<FireStation> delete(@PathVariable(value = "id") long id) {
        Optional<FireStation> fireStation = fireStationService.findById(id);
        if (fireStation.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            fireStationService.delete(fireStation.get().getId());
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public ResponseEntity<FireStationCoverage> getPersonByStationNumber(@RequestParam(value = "stationNumber") long id) {
        FireStationCoverage result = fireStationService.findAllByFireStationNumber(id);

            if (result == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(result);
            }
        }

    @GetMapping(value = "/phoneAlert", params = "firestation")
    public ResponseEntity<PhoneAlert> getPhoneAlert(@RequestParam("firestation") long stationNumber) {
        PhoneAlert result = fireStationService.createPhoneAlert(stationNumber);
        if(result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(value = "flood/stations", params = "stationNumber")
    public ResponseEntity<Flood> getFlood(@RequestParam("stationNumber") List<Long> stationNumberList) {
        Flood result = fireStationService.createFlood(stationNumberList);
        if(result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
