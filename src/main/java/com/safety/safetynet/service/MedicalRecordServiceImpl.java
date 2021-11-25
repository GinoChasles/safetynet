package com.safety.safetynet.service;

import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.repository.MedicalRecordRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * The type Medical record service.
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
  /**
   * The Repository.
   */
  private final MedicalRecordRepository repository;

  /**
   * Instantiates a new Medical record service.
   *
   * @param repository1 the repository
   */
  public MedicalRecordServiceImpl(final MedicalRecordRepository repository1) {
    this.repository = repository1;
  }

  /**
   * FindById.
   *
   * @param id the id
   * @return optional of medicalrecords
   */
  @Override
  public Optional<MedicalRecords> findById(final int id) {
    return repository.findById(id);
  }

  /**
   * Insert.
   *
   * @param medicalRecords the medical records
   * @return medicalrecord
   */
  @Override
  public MedicalRecords insert(final MedicalRecords medicalRecords) {
    return repository.save(medicalRecords);
  }

  /**
   * Delete.
   *
   * @param id the id
   */
  @Override
  public void delete(final int id) {
    Optional<MedicalRecords> medicalRecord = this.findById(id);
    medicalRecord.ifPresent(m -> repository.delete(m));
  }

  /**
   * Update.
   *
   * @param id             the id
   * @param medicalRecords the medical records
   * @return medicalrecord update
   */
  @Override
  public MedicalRecords update(final int id,
                               final MedicalRecords medicalRecords) {
    Optional<MedicalRecords> mr1 = this.findById(id);
    if (mr1.isPresent()) {
      MedicalRecords medicalRecordsToUpdate = mr1.get();
      medicalRecordsToUpdate.setBirthdate(medicalRecords.getBirthdate());
      medicalRecordsToUpdate.setMedications(
          medicalRecords.getMedications());
      medicalRecordsToUpdate.setAllergies(medicalRecords.getAllergies());
      medicalRecordsToUpdate.setFirstName(medicalRecords.getFirstName());
      medicalRecordsToUpdate.setLastName(medicalRecords.getLastName());

      return repository.save(medicalRecordsToUpdate);
    } else {
      return null;
    }
  }

  /**
   * Delete by name.
   *
   * @param firstName the first name
   * @param lastName  the last name
   */
  @Override
  public void deleteByName(final String firstName, final String lastName) {
    Optional<MedicalRecords> p1 =
        repository.findByFirstNameAndLastName(firstName, lastName);
    p1.ifPresent(p -> repository.delete(p));
  }

  /**
   * FindAll.
   *
   * @return list of medicalrecords
   */
  @Override
  public List<MedicalRecords> findAll() {
    return repository.findAll();
  }


}
