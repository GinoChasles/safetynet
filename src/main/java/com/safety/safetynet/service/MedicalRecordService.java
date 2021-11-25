package com.safety.safetynet.service;

import com.safety.safetynet.model.MedicalRecords;
import java.util.List;
import java.util.Optional;

/**
 * The interface Medical record service.
 */
public interface MedicalRecordService {
  /**
   * Find by id optional.
   *
   * @param id the id
   * @return the optional
   */
  Optional<MedicalRecords> findById(int id);

  /**
   * Insert medical records.
   *
   * @param medicalRecords the medical records
   * @return the medical records
   */
  MedicalRecords insert(MedicalRecords medicalRecords);

  /**
   * Delete.
   *
   * @param id the id
   */
  void delete(int id);

  /**
   * Update medical records.
   *
   * @param id             the id
   * @param medicalRecords the medical records
   * @return the medical records
   */
  MedicalRecords update(int id, MedicalRecords medicalRecords);

  /**
   * Delete by name.
   *
   * @param firstName the first name
   * @param lastName  the last name
   */
  void deleteByName(String firstName, String lastName);

  /**
   * Find all list.
   *
   * @return the list
   */
  List<MedicalRecords> findAll();
}
