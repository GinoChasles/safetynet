package com.safety.safetynet.service;

import com.safety.safetynet.model.Person;
import com.safety.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;

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
//
//    }
//
//    @Test
//    public void findChildAlertTest() {
//
//    }
//
//    @Test
//    public void createCommunityEmailTest() {
//
//    }
//
//    @Test
//    public void createFireTest() {
//
//    }
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
//    @Test
//    public void createPersonInfoListTest() {
//
//    }
}
