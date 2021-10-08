package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.FireStationCoverage;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.model.PersonInfos;
import com.safety.safetynet.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FireStationService implements CrudService<FireStation> {
    @Autowired
    FireStationRepository repository;
    @Autowired
    PersonService personService;

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
            fireStationToUpdate.setStationNumber(fireStation.getStationNumber());

            return repository.save(fireStationToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteByName(String firstName, String lastName) {

    }

    @Override
    public List<FireStation> findAll() {
        return repository.findAll();
    }


    //get persons by stationnumber
    public FireStationCoverage findAllByFireStationNumber(long stationNumber) {
        //on récupère les stations dont le stationNumber correspond
        List<FireStation> fireStations = repository.findAllByStationNumber(stationNumber);
        List<String> addresses = new ArrayList<>();
        FireStationCoverage result = new FireStationCoverage();
        //on ajout les adresses des stations dans une liste
        for(int i=0; i < fireStations.size(); i++) {
            addresses.add(fireStations.get(i).getAddress());
        }
        //on récupère les personnes dont l'adresse correspond aux adresses des firestations
        List<Person> personList = personService.findAllByAdressIn(addresses);
        // pour chaque personne on vient créer les infos d'une personne

        result = personService.createPersonInfoToStationNumber(personList);
        return result;
    }
}
