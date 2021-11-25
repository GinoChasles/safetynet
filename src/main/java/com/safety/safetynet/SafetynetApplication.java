package com.safety.safetynet;

import com.safety.safetynet.data.DataObject;
import com.safety.safetynet.data.JsonReaderWriter;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.repository.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * The type Safetynet application.
 */
@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
public class SafetynetApplication {
  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(final String[] args) {
    SpringApplication.run(SafetynetApplication.class, args);
  }

  /**
   * Runner command line runner.
   *
   * @param personRepository        the person repository
   * @param fireStationRepository   the fire station repository
   * @param medicalRecordRepository the medical record repository
   * @param medicationRepository    the medication repository
   * @param allergiesRepository     the allergies repository
   * @return the command line runner
   */
  @Bean
  CommandLineRunner runner(final PersonRepository personRepository,
                           final FireStationRepository fireStationRepository,
                          final MedicalRecordRepository medicalRecordRepository,
                           final MedicationRepository medicationRepository,
                           final AllergiesRepository allergiesRepository) {
    return args -> {
      JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();
      DataObject dataObject;
      dataObject = jsonReaderWriter.read();
      Logger logger = LoggerFactory.getLogger(this.getClass());

      try {
        personRepository.saveAll(dataObject.getPersons());
        fireStationRepository.saveAll(dataObject.getFirestations());

        List<Medications> medicationsList = new ArrayList<>();
        Set<Allergies> allergiesList = new HashSet<>();
        Medications medications;
        Allergies allergies;
        for (int i = 0; i < dataObject
            .getMedicalrecords().size(); i++) {
          for (int j = 0; j < dataObject.getMedicalrecords()
              .get(i).getMedications().size(); j++) {

            medications =
                dataObject.getMedicalrecords().get(i).getMedications().get(j);
            medicationsList.add(medications);
          }
          for (Allergies allergies1 : dataObject.getMedicalrecords()
              .get(i).getAllergies()) {
            allergies = allergies1;
            allergiesList.add(allergies);
          }
        }
        medicationRepository.saveAll(medicationsList);
        allergiesRepository.saveAll(allergiesList);

        medicalRecordRepository
            .saveAll(dataObject.getMedicalrecords());
        logger.info("data saved!");
      } catch (Exception e) {
        logger.error("Data don't saved !", e);
      }
    };
  }

  /**
   * for actuators.
   *
   * @return endpoint in memory http trace repository
   */
  @Bean
  InMemoryHttpTraceRepository httptrace() {

    return new InMemoryHttpTraceRepository();

  }
}
