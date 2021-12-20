package com.safety.safetynet.service;

import com.safety.safetynet.dto.Child;
import com.safety.safetynet.dto.ChildAlert;
import com.safety.safetynet.dto.CommunityEmail;
import com.safety.safetynet.dto.Fire;
import com.safety.safetynet.dto.PersonInfo;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.repository.FireStationRepository;
import com.safety.safetynet.repository.MedicalRecordRepository;
import com.safety.safetynet.repository.PersonRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private FireStationRepository fireStationRepository;
    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;
    private Person person2;
    private Person person3;
    private static MedicalRecords medicalRecords1 = new MedicalRecords();
    private static MedicalRecords medicalRecords2 = new MedicalRecords();
    private static MedicalRecords medicalRecords3 = new MedicalRecords();
    private static List<Person> personList = new ArrayList<>();
    private static List<Person> personListFire = new ArrayList<>();
    private static FireStation fireStation = new FireStation();
    private static List<Medications> medicationsList = new ArrayList<>();
    private static Set<Allergies> allergiesSet = new HashSet<>();
    Medications medications = new Medications();
    Allergies allergies = new Allergies();
    List<Medications> emptyMedication = new ArrayList<>();
    Set<Allergies> emptyAllergies = new HashSet<>();


    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1);
        person.setFirstName("TestFirstName");
        person.setLastName("TestLastName");
        person.setPhone("333-3333");
        person.setAddress("3 Rue bidon");
        person.setEmail("test@mail.fr");
        person.setZip(12345);
        person.setCity("TestCity");
        person2 = new Person(2, "FirstTest2", "LastTest2", "3 Rue bidon", "TestCity", 12345, "12345", "TEST");
        person3 = new Person(3, "FirstTest3", "LastTest2", "3 Rue bidon", "TestCity", 12345, "33333", "TEST33");

        medications.setMedication("2mg Doliprane");
        allergies.setAllergies("Peanut");

        medicationsList.add(medications);
        allergiesSet.add(allergies);

        medicalRecords1 = new MedicalRecords(1,"TestFirstName","TestLastName", LocalDate.of(2012,12,12), medicationsList, allergiesSet);
        medicalRecords2 = new MedicalRecords(2,"FirstTest2","LastTest2", LocalDate.of(2001,12,12), emptyMedication, emptyAllergies);
        medicalRecords3 = new MedicalRecords(3,"FirstTest3","LastTest2", LocalDate.of(2002,12,12), null, null);

        personList.add(person);
        personList.add(person2);
        personListFire.add(person2);
        personListFire.add(person3);

        fireStation.setStation(3);
        fireStation.setAddress("3 Rue bidon");
    }

    @Test
    public void findByIdTest() {
        Mockito.when(personRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(person));
        Optional<Person> personOptional = personService.findById(1);
        Person person = new Person();
        if(personOptional.isPresent()){
            person = personOptional.get();
        }
        assertThat(person.getFirstName()).isEqualTo("TestFirstName");
        Mockito.verify(personRepository, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    public void insertTest() {
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
        Person personToSave = personService.insert(person);

        assertThat(personToSave.getId()).isNotNull();
        assertThat(personToSave.getFirstName()).isEqualTo("TestFirstName");
    }

    @Test
    public void deleteTest() {
        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        Optional<Person> personOptional = personRepository.findById(person.getId());
        Person personToDelete;
        if(personOptional.isPresent()) {
            personToDelete = personOptional.get();
            personService.delete(personToDelete.getId());
            Mockito.verify(personRepository, Mockito.times(1)).delete(argumentCaptor.capture());
        }
    }

    @Test
    public void updateTest() {
        Optional<Person> personOptional = personRepository.findById(person.getId());
        Person personToUpdate;
        if(personOptional.isPresent()) {
            personToUpdate = personOptional.get();
            personToUpdate.setFirstName("FirstNameUpdate");
            Mockito.when(personService.update(person.getId(), personToUpdate)).thenReturn(person);
            assertThat(person.getFirstName()).isEqualTo("FirstNameUpdate");
        }
    }

    @Test
    public void deleteByNameTest() {
        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);

        Optional<Person> personOptional = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        Person personToDelete;
        if(personOptional.isPresent()) {
            personToDelete = personOptional.get();
            personService.deleteByName(personToDelete.getFirstName(), personToDelete.getLastName());
            Mockito.verify(personRepository, Mockito.times(1)).delete(argumentCaptor.capture());
        }
    }

    @Test
    public void findAllTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(person);

        Mockito.when(personRepository.findAll()).thenReturn(personList);

        Iterable<Person> personIterable = personService.findAll();
        Person person1 = personIterable.iterator().next();
        assertThat(person1.getFirstName()).isEqualTo("TestFirstName");
        Mockito.verify(personRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findAllByAdressInTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        personList.add(person);

        List<String> adressList = new ArrayList<>();
        adressList.add(person.getAddress());

        Mockito.when(personRepository.findAllByAddressIn(adressList)).thenReturn(personList);
        assertThat(personService.findAllByAdressIn(adressList)).size().isEqualTo(2);
        Mockito.verify(personRepository, Mockito.times(1)).findAllByAddressIn(adressList);
    }

//    @Test
//    public void createPersonInfoToStationNumberTest() {
//        FireStationCoverage fireStationCoverage = new FireStationCoverage();
//        List<PersonInfosForCoverage> result = new ArrayList<>();
//        PersonInfosForCoverage personInfosForCoverageLocal = new PersonInfosForCoverage();
//
//
//        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecords1.getBirthdate());
//
//
//    }

    @Test
    public void findChildAlertTest() {
        ChildAlert result = new ChildAlert();
        List<Child> children = new ArrayList<>();
        Child childLocal = new Child("TestFirstName","TestLastName", 9);
        children.add(childLocal);

        List<Person> personListLocal = new ArrayList<>();
        personListLocal.add(person2);

        result.setChildren(children);
        result.setFamily(personListLocal);

        lenient().when(personRepository.findAllByAddress("3 Rue bidon")).thenReturn(personList);
        lenient().when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(), person.getFirstName())).thenReturn(medicalRecords1.getBirthdate());
        lenient().when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getFirstName())).thenReturn(medicalRecords2.getBirthdate());

        ChildAlert mock = personService.findChildAlert("3 Rue bidon");
    System.out.println(mock);
        assertThat(mock.getChildren()).isEqualTo(result.getChildren());
        assertThat(mock.getFamily()).isEqualTo(result.getFamily());

    }

    @Test
    public void createCommunityEmailTest() {
        CommunityEmail result = new CommunityEmail();
        List<String> email = new ArrayList<>();
        email.add("TEST");
        email.add("test@mail.fr");
        result.setEmail(email);

        when(personRepository.findAllByCity("TestCity")).thenReturn(personList);

        CommunityEmail mock = personService.createCommunityEmail("TestCity");
        assertThat(mock.getEmail()).isEqualTo(result.getEmail());
    }

    @Test
    public void createFireTest() {
        List<Fire> result = new ArrayList<>();

        String address = "3 Rue bidon";


        Fire fireLocal1 = new Fire(3, "TestFirstName", "TestLastName", "333-3333", 9, medicationsList, allergiesSet);
        Fire fireLocal2 = new Fire(3, "FirstTest2", "LastTest2", "12345", 20, emptyMedication, emptyAllergies);
        result.add(fireLocal1);
        result.add(fireLocal2);

        when(personRepository.findAllByAddress(address)).thenReturn(personList);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person.getFirstName(),
            person.getLastName())).thenReturn(medicalRecords1.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(),
            person2.getLastName())).thenReturn(medicalRecords2.getBirthdate());
        when(fireStationRepository.findFireStationByAddress(address)).thenReturn(fireStation);
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(),
            person.getLastName())).thenReturn(Optional.ofNullable(medicalRecords1));
        when(medicalRecordRepository.findByFirstNameAndLastName(person2.getFirstName(),
            person2.getLastName())).thenReturn(Optional.ofNullable(medicalRecords2));

        List<Fire> mock = personService.createFire(address);

        assertThat(mock.get(0).getFirstName()).isEqualTo(result.get(0).getFirstName());
        assertThat(mock.get(0).getLastName()).isEqualTo(result.get(0).getLastName());
        assertThat(mock.get(0).getAge()).isEqualTo(result.get(0).getAge());
        assertThat(mock.get(0).getPhone()).isEqualTo(result.get(0).getPhone());
        assertThat(mock.get(0).getStationNumber()).isEqualTo(result.get(0).getStationNumber());
        assertThat(mock.get(0).getAllergies()).isEqualTo(result.get(0).getAllergies());
        assertThat(mock.get(0).getMedications()).isEqualTo(result.get(0).getMedications());

        assertThat(mock.get(1).getMedications()).isEqualTo(result.get(1).getMedications());
        assertThat(mock.get(1).getAllergies()).isEqualTo(result.get(1).getAllergies());
        assertThat(mock.get(1).getFirstName()).isEqualTo(result.get(1).getFirstName());
        assertThat(mock.get(1).getLastName()).isEqualTo(result.get(1).getLastName());
        assertThat(mock.get(1).getAge()).isEqualTo(result.get(1).getAge());
        assertThat(mock.get(1).getPhone()).isEqualTo(result.get(1).getPhone());
        assertThat(mock.get(1).getStationNumber()).isEqualTo(result.get(1).getStationNumber());
    }

    @Test
    public void createPersonInfoListTest() {
        List<PersonInfo> personInfoListLocal = new ArrayList<>();
        PersonInfo personInfoLocal = new PersonInfo("FirstTest2", "LastTest2", "3 Rue bidon", 20, "TEST", medicalRecords2.getMedications(), medicalRecords2.getAllergies());
        PersonInfo personInfoLocal2 = new PersonInfo("FirstTest3", "LastTest2", "3 Rue bidon", 19, "TEST33", medicalRecords3.getMedications(), medicalRecords3.getAllergies());
        personInfoListLocal.add(personInfoLocal);
        personInfoListLocal.add(personInfoLocal2);

        when(personRepository.findAllByLastName(person3.getLastName())).thenReturn(personListFire);
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(medicalRecords2.getBirthdate());
        when(medicalRecordRepository.findBirthDateByFirstNameAndLastName(person3.getFirstName(), person3.getLastName())).thenReturn(medicalRecords3.getBirthdate());
        when(medicalRecordRepository.findByFirstNameAndLastName(person2.getFirstName(), person2.getLastName())).thenReturn(Optional.ofNullable(medicalRecords2));
        when(medicalRecordRepository.findByFirstNameAndLastName(person3.getFirstName(), person3.getLastName())).thenReturn(Optional.ofNullable(medicalRecords3));

        List<PersonInfo> mock = personService.createPersonInfoList(person2.getFirstName(),
            person2.getLastName());

        assertThat(mock.get(0).getLastName()).isEqualTo(personInfoListLocal.get(0).getLastName());
        assertThat(mock.get(0).getLastName()).isEqualTo(personInfoListLocal.get(1).getLastName());
        assertThat(mock.get(0).getAllergies()).isEqualTo(personInfoListLocal.get(0).getAllergies());
        assertThat(mock.get(1).getAllergies()).isEqualTo(personInfoListLocal.get(1).getAllergies());
        assertThat(mock.get(0).getFirstName()).isEqualTo(personInfoListLocal.get(0).getFirstName());
        assertThat(mock.get(1).getFirstName()).isEqualTo(personInfoListLocal.get(1).getFirstName());
        assertThat(mock.get(0).getAge()).isEqualTo(personInfoListLocal.get(0).getAge());
        assertThat(mock.get(1).getAge()).isEqualTo(personInfoListLocal.get(1).getAge());
        assertThat(mock.get(0).getMedications()).isEqualTo(personInfoListLocal.get(0).getMedications());
        assertThat(mock.get(1).getMedications()).isEqualTo(personInfoListLocal.get(1).getMedications());

    }
//
//    @Test
//    public void insertAllTest() {
//
//        List<Person> personList = new ArrayList<>();
//        personList.add(person);
//        personList.add(person);
//        List<Person> personList1 = personService.insertAll(personList);
//
//        Mockito.when(personRepository.saveAll(personList)).thenReturn(personList);
//        assertThat(personList1.size()).isEqualTo(2);
//        Mockito.verify(personRepository, Mockito.times(1)).saveAll(personList);
//    }
}
