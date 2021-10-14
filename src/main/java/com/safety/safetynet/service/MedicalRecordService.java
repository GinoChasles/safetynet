package com.safety.safetynet.service;

import com.safety.safetynet.model.MedicalRecord;
import com.safety.safetynet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService implements CrudService<MedicalRecord> {
    @Autowired
    MedicalRecordRepository repository;

    @Override
    public Optional<MedicalRecord> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public MedicalRecord insert(MedicalRecord medicalRecord) {
        return repository.save(medicalRecord);
    }

    @Override
    public void delete(long id) {
        Optional<MedicalRecord> medicalRecord = this.findById(id);
        medicalRecord.ifPresent(m -> repository.delete(m));
    }

    @Override
    public MedicalRecord update(long id, MedicalRecord medicalRecord) {
        Optional<MedicalRecord> mr1 = this.findById(id);
        if (mr1.isPresent()) {
            MedicalRecord medicalRecordToUpdate = mr1.get();
            medicalRecordToUpdate.setBirthdate(medicalRecord.getBirthdate());
            medicalRecordToUpdate.setMedications(medicalRecord.getMedications());
            medicalRecordToUpdate.setAllergies(medicalRecord.getAllergies());

            return repository.save(medicalRecordToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteByName(String firstName, String lastName) {
        Optional<MedicalRecord> p1 = repository.findByFirstNameAndLastName(firstName, lastName);
        p1.ifPresent(p -> repository.delete(p));
    }

    @Override
    public List<MedicalRecord> findAll() {
        return repository.findAll();
    }


}
