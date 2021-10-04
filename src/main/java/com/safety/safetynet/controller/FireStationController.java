package com.safety.safetynet.controller;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.FireStationService;
import com.safety.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(result.isEmpty()){
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
        if(fireStation1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(fireStation1);
        }
    }

    @DeleteMapping(value = "/firestation/{id}")
    public ResponseEntity<FireStation> delete(@PathVariable(value = "id") long id) {
        Optional<FireStation> fireStation = fireStationService.findById(id);
        if(fireStation.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            fireStationService.delete(fireStation.get().getId());
            return ResponseEntity.accepted().build();
        }
    }

    @GetMapping("/firestation?stationNumber={stationNumber}")
    public ResponseEntity<List<Person>> getPersonByStationNumber(@PathVariable(value = "stationNumber") long id) {
        List<Person> result = personService.findByNumberStation(id);
        if(result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }
}
