package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
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
    @InjectMocks
    private PersonServiceImpl personService;
    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    private FireStation fireStation;

    @BeforeEach
    void setUp() {
        fireStation = new FireStation();
        fireStation.setId(1);
        fireStation.setAddress("3 Test");
        fireStation.setStation(2);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(fireStationRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(fireStation));
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
        Mockito.when(fireStationRepository.save(Mockito.any(FireStation.class))).thenReturn(fireStation);
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
            Mockito.when(fireStationService.update(fireStation.getId(), fireStationToUpdate)).thenReturn(fireStation);
            assertThat(fireStation.getAddress()).isEqualTo("Test");
        }
    }

    @Test
    public void findAllTest() {
        List<FireStation> fireStationList = new ArrayList<>();
        fireStationList.add(fireStation);

        Mockito.when(fireStationRepository.findAll()).thenReturn(fireStationList);

        Iterable<FireStation> fireStationIterable = fireStationService.findAll();
        FireStation fireStation1 = fireStationIterable.iterator().next();
        assertThat(fireStation1.getAddress()).isEqualTo("3 Test");
        Mockito.verify(fireStationRepository, Mockito.times(1)).findAll();

    }

//    @Test
//    public void findAllByFireStationNumberTest() {
//        FireStationCoverage fireStationCoverage = new FireStationCoverage();
//        List<PersonInfosForCoverage> personInfosList = new ArrayList<>();
//        fireStationCoverage.setAdult(1);
//        fireStationCoverage.setChild(1);
//
//        PersonInfosForCoverage personInfos1 = new PersonInfosForCoverage("FirstTest1", "LastTest1", "3 Test", "1234");
//        PersonInfosForCoverage personInfos2 = new PersonInfosForCoverage("FirstTest2", "LastTest2", "3 Test", "1234");
//        personInfosList.add(personInfos1);
//        personInfosList.add(personInfos2);
//        fireStationCoverage.setPersonInfosList(personInfosList);
//
//        List<FireStation> fireStationList = new ArrayList<>();
//        fireStationList.add(fireStation);
//
//        List<String> address = new ArrayList<>();
//        address.add(fireStation.getAddress());
//
//        List<Person> personList = new ArrayList<>();
//        Person person = new Person(1, "FirstTest1", "LastTest1", "3 Test", "city", 12345, "1234", "TEST");
//        Person person2 = new Person(2, "FirstTest2", "LastTest2", "3 Test", "city", 12345, "1234", "TEST");
//
//        personList.add(person);
//        personList.add(person2);
//
//        MedicalRecords medicalRecords1 = new MedicalRecords(1,"FirstTest1","LastTest1", LocalDate.of(2012,12,12), null, null);
//        MedicalRecords medicalRecords2 = new MedicalRecords(2,"FirstTest2","LastTest2", LocalDate.of(2000,12,12), null, null);
//
//
//        Mockito.when(fireStationRepository.findAllByStation(1)).thenReturn(fireStationList);
//        Mockito.when(personService.findAllByAdressIn(address)).thenReturn(personList);
////        Mockito.when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(LocalDate.of(2012,12,12));
//        Mockito.when(personService.createPersonInfoToStationNumber(personList)).thenReturn(fireStationCoverage);
//
//        FireStationCoverage result = fireStationService.findAllByFireStationNumber(1);
//        assertThat(result.getAdult()).isEqualTo(1);
//        assertThat(result.getChild()).isEqualTo(1);
//        assertThat(result.getPersonInfosList()).isEqualTo(personInfosList);
//    }
//
//    @Test
//    public void createPhoneAlertTest() {
//
//    }
//
//    @Test
//    public void createFloodTest() {
//
//    }

}

