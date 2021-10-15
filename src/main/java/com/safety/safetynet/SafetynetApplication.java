package com.safety.safetynet;

import com.safety.safetynet.data.DataObject;
import com.safety.safetynet.data.JsonReaderWriter;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
public class SafetynetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(PersonRepository personService, FireStationRepository fireStationService, MedicalRecordRepository medicalRecordService) {
		return args -> {
			JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();
			DataObject dataObject = new DataObject();
			dataObject = jsonReaderWriter.read();
			personService.saveAll(dataObject.getPersons());
			fireStationService.saveAll(dataObject.getFirestations());
			medicalRecordService.saveAll(dataObject.getMedicalrecords());

		};
	}
}
