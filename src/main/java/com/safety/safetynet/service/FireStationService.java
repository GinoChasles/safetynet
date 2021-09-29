package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FireStationService implements CrudService<FireStation> {
    @Autowired
    FireStationRepository repository;

    @Override
    public Optional<FireStation> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public FireStation insert(FireStation fireStation) {
        return repository.save(fireStation);
    }

    @Override
    public void delete(long id) {
        Optional<FireStation> fireStation = this.findById(id);
        fireStation.ifPresent(station -> repository.delete(station));
    }

    @Override
    public FireStation update(long id, FireStation fireStation) {
        Optional<FireStation> fireStation1 = this.findById(id);
        if (fireStation1.isPresent()) {
            FireStation fireStationToUpdate = fireStation1.get();
            fireStationToUpdate.setStation(fireStation.getStation());

            return repository.save(fireStationToUpdate);
        } else {
            return null;
        }
    }
}
