package com.safety.safetynet;

import com.safety.safetynet.data.DataObject;
import com.safety.safetynet.data.JsonReaderWriter;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
public class SafetynetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository, MedicationRepository medicationRepository, AllergiesRepository allergiesRepository) {
		return args -> {
			JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();
			DataObject dataObject;
			dataObject = jsonReaderWriter.read();
			try {
				personRepository.saveAll(dataObject.getPersons());
				fireStationRepository.saveAll(dataObject.getFirestations());

				List<Medications> medicationsList = new ArrayList<>();
				Set<Allergies> allergiesList = new HashSet<>();
				Medications medications;
				Allergies allergies;
				for(int i = 0; i < dataObject.getMedicalrecords().size(); i++) {
					for(int j = 0; j < dataObject.getMedicalrecords().get(i).getMedications().size(); j++) {

					medications = dataObject.getMedicalrecords().get(i).getMedications().get(j);
					medicationsList.add(medications);
					}
					for(Allergies allergies1 : dataObject.getMedicalrecords().get(i).getAllergies()) {
						allergies = allergies1;
						allergiesList.add(allergies);
					}
				}
				medicationRepository.saveAll(medicationsList);
				allergiesRepository.saveAll(allergiesList);

				medicalRecordRepository.saveAll(dataObject.getMedicalrecords());
				System.out.println("Data saved !");
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Data don't saved !");
			}
		};
	}
}
