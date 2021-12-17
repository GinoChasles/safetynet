package com.safety.safetynet.service;

import com.safety.safetynet.dto.FireStationCoverage;
import com.safety.safetynet.dto.Flood;
import com.safety.safetynet.dto.PersonInfosForCoverage;
import com.safety.safetynet.dto.PhoneAlert;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

    @Mock
    private FireStationRepository fireStationRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private FireStationServiceImpl fireStationService;
    @Mock
    private PersonServiceImpl personService;
    @Mock
    private MedicalRecordServiceImpl medicalRecordService;

    private static FireStation fireStation;
    private static List<FireStation> fireStationList = new ArrayList<>();
    private static List<String> address = new ArrayList<>();
    private static List<Person> personList = new ArrayList<>();
    private static MedicalRecords medicalRecords1 = new MedicalRecords();
    private static MedicalRecords medicalRecords2 = new MedicalRecords();
    private static Person person = new Person();
    private static Person person2 = new Person();

    @BeforeEach
    void setUp() {
        fireStation = new FireStation();
        fireStation.setId(1);
        fireStation.setAddress("3 Test");
        fireStation.setStation(2);

        fireStationList.add(fireStation);

        address.add(fireStation.getAddress());

        person = new Person(1, "FirstTest1", "LastTest1", "3 Test", "city", 12345, "1234", "TEST");
        person2 = new Person(2, "FirstTest2", "LastTest2", "3 Test", "city", 12345, "12345", "TEST");

        personList.add(person);
        personList.add(person2);

        medicalRecords1 = new MedicalRecords(1,"FirstTest1","LastTest1", LocalDate.of(2012,12,12), null, null);
        medicalRecords2 = new MedicalRecords(2,"FirstTest2","LastTest2", LocalDate.of(2001,12,12), null, null);
    }

    @Test
    public void findByIdTest() {
        when(fireStationRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(fireStation));
        Optional<FireStation> fireStationOptional = fireStationService.findById(1);
        FireStation fireStation = new FireStation();
        if(fireStationOptional.isPresent()) {
            fireStation = fireStationOptional.get();
        }
        assertThat(fireStation.getAddress()).isEqualTo("3 Test");
        Mockito.verify(fireStationRepository, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    public void insertTest() {
        when(fireStationRepository.save(Mockito.any(FireStation.class))).thenReturn(fireStation);
        FireStation fireStation1 = fireStationService.insert(fireStation);

        assertThat(fireStation1.getId()).isNotNull();
        assertThat(fireStation1.getAddress()).isEqualTo("3 Test");
    }

    @Test
    public void deleteTest() {
        ArgumentCaptor<FireStation> argumentCaptor = ArgumentCaptor.forClass(FireStation.class);
        Optional<FireStation> fireStationOptional = fireStationRepository.findById(fireStation.getId());
        FireStation fireStationToDelete;
        if(fireStationOptional.isPresent()) {
            fireStationToDelete = fireStationOptional.get();
            fireStationService.delete(fireStationToDelete.getId());
            Mockito.verify(fireStationRepository, Mockito.times(1)).delete(argumentCaptor.capture());
        }
    }

    @Test
    public void updateTest() {
        Optional<FireStation> fireStationOptional = fireStationRepository.findById(fireStation.getId());
        FireStation fireStationToUpdate;
        if(fireStationOptional.isPresent()) {
            fireStationToUpdate = fireStationOptional.get();
            fireStationToUpdate.setAddress("Test");
            when(fireStationService.update(fireStation.getId(), fireStationToUpdate)).thenReturn(fireStation);
            assertThat(fireStation.getAddress()).isEqualTo("Test");
        }
    }

    @Test
    public void findAllTest() {
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        when(fireStationRepository.findAll()).thenReturn(fireStationList);

        Iterable<FireStation> fireStationIterable = fireStationService.findAll();
        FireStation fireStation1 = fireStationIterable.iterator().next();
        assertThat(fireStation1.getAddress()).isEqualTo("3 Test");
        Mockito.verify(fireStationRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllByFireStationNumberTest() {
        FireStationCoverage fireStationCoverage = new FireStationCoverage();
        List<PersonInfosForCoverage> personInfosList = new ArrayList<>();
        fireStationCoverage.setAdult(1);
        fireStationCoverage.setChild(1);

        PersonInfosForCoverage personInfos1 = new PersonInfosForCoverage("FirstTest1", "LastTest1", "3 Test", "1234");
        PersonInfosForCoverage personInfos2 = new PersonInfosForCoverage("FirstTest2", "LastTest2", "3 Test", "1234");
        personInfosList.add(personInfos1);
        personInfosList.add(personInfos2);
        fireStationCoverage.setPersonInfosList(personInfosList);

        when(fireStationRepository.findAllByStation(  1)).thenReturn(fireStationList);
        when(personService.findAllByAdressIn(address)).thenReturn(personList);
//        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecords1.getBirthdate());
//        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(medicalRecords2.getBirthdate());
        when(personService.createPersonInfoToStationNumber(personList)).thenReturn(fireStationCoverage);

        FireStationCoverage result = fireStationService.findAllByFireStationNumber(1);
        assertThat(result.getAdult()).isEqualTo(1);
        assertThat(result.getChild()).isEqualTo(1);
        assertThat(result.getPersonInfosList()).isEqualTo(personInfosList);
    }

    @Test
    public void createPhoneAlertTest() {
        PhoneAlert result = new PhoneAlert();
        List<String> phoneList = new ArrayList<>();
        phoneList.add(person.getPhone());
        phoneList.add(person2.getPhone());
        result.setPhoneList(phoneList);

        when(fireStationRepository.findAllByStation(1)).thenReturn(fireStationList);
        when(personService.findAllByAdressIn(address)).thenReturn(personList);
        PhoneAlert mock = fireStationService.createPhoneAlert(1);

        assertThat(mock.getPhoneList()).isEqualTo(phoneList);
    }

    @Test
    public void createFloodTest() {
        List<Map<String, List<Flood>>> entry = new ArrayList<>();
        List<Integer> stationNumberList = new ArrayList<>();
        stationNumberList.add(fireStation.getStation());

        Flood flood = new Flood(person.getFirstName(), person.getLastName(), 9, person.getPhone(), medicalRecords1.getMedications(), medicalRecords1.getAllergies());
        Flood flood2 = new Flood(person2.getFirstName(), person2.getLastName(), 20, person2.getPhone(), medicalRecords2.getMedications(), medicalRecords2.getAllergies());
        List<Flood> floodList = new ArrayList<>();
        floodList.add(flood);
        floodList.add(flood2);

        Map<String, List<Flood>> mapLocal = new HashMap<>();
        mapLocal.put(fireStation.getAddress(), floodList);
        entry.add(mapLocal);


        when(fireStationRepository.findFireStationByStation(2)).thenReturn(fireStationList);
        when(personRepository.findAllByAddress(address.get(0))).thenReturn(personList);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecords1.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(medicalRecords2.getBirthdate());
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(
            Optional.ofNullable(medicalRecords1));
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(
            Optional.ofNullable(medicalRecords2));

        List<Map<String, List<Flood>>> result = fireStationService.createFlood(stationNumberList);

        assertThat(result.get(0).get(fireStation.getAddress()).get(0).getFirstName()).isEqualTo(entry.get(0).get(fireStation.getAddress()).get(0).getFirstName());
        assertThat(result.get(0).get(fireStation.getAddress()).get(0).getLastName()).isEqualTo(entry.get(0).get(fireStation.getAddress()).get(0).getLastName());
        assertThat(result.get(0).get(fireStation.getAddress()).get(0).getPhone()).isEqualTo(entry.get(0).get(fireStation.getAddress()).get(0).getPhone());

    }

}

