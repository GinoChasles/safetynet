package com.safety.safetynet.service;

import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Medical record service.
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    /**
     * The Repository.
     */
    MedicalRecordRepository repository;

    /**
     * Instantiates a new Medical record service.
     *
     * @param repository the repository
     */
    public MedicalRecordServiceImpl(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MedicalRecords> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public MedicalRecords insert(MedicalRecords medicalRecords) {
        return repository.save(medicalRecords);
    }

    @Override
    public void delete(int id) {
        Optional<MedicalRecords> medicalRecord = this.findById(id);
        medicalRecord.ifPresent(m -> repository.delete(m));
    }

    @Override
    public MedicalRecords update(int id, MedicalRecords medicalRecords) {
        Optional<MedicalRecords> mr1 = this.findById(id);
        if (mr1.isPresent()) {
            MedicalRecords medicalRecordsToUpdate = mr1.get();
            medicalRecordsToUpdate.setBirthdate(medicalRecords.getBirthdate());
            medicalRecordsToUpdate.setMedications(medicalRecords.getMedications());
            medicalRecordsToUpdate.setAllergies(medicalRecords.getAllergies());
            medicalRecordsToUpdate.setFirstName(medicalRecords.getFirstName());
            medicalRecordsToUpdate.setLastName(medicalRecords.getLastName());

            return repository.save(medicalRecordsToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteByName(String firstName, String lastName) {
        Optional<MedicalRecords> p1 = repository.findByFirstNameAndLastName(firstName, lastName);
        p1.ifPresent(p -> repository.delete(p));
    }

    @Override
    public List<MedicalRecords> findAll() {
        return repository.findAll();
    }


}
