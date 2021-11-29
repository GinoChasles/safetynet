package com.safety.safetynet.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynet.SafetynetApplication;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.model.Person;
import com.safety.safetynet.service.PersonServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = SafetynetApplication.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    PersonServiceImpl personService;

    @Test
    @Order(1)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void addPersonTest() throws Exception {
        Person person = new Person(1, "Test", "Test", "testaddresse", "citytest", 12345, "phonetest", "emailtest");
        String json = mapper.writeValueAsString(person);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void updateTest() throws Exception {
        Person person = new Person(1, "Test", "Test", "testaddresse", "citytest", 12345, "phonetest", "emailtest");
        String json = mapper.writeValueAsString(person);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/person/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    @Order(6)

    public void getChildAlertTest() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void getCommunityEmailTest() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void getFireTest() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void getOnePersonByIdTest_whenEmpty() throws Exception {

        Optional<Person> person = personService.findById(100);
        if (person.isEmpty()) {
            mockMvc.perform(get("/person/{id}", 100))
                .andExpect(status().isNotFound());
        }
        Mockito.verify(personService, Mockito.times(1)).findById(100);
    }

    @Test
    public void updatePersonTest_whenEmpty() throws Exception {
        Person person1 = new Person(100, "Test", "Test", "testaddresse", "citytest", 12345, "phonetest", "emailtest");

        String json = mapper.writeValueAsString(person1);

        Person person =
            personService.update(person1.getId(), person1);
        if (person == null) {
            mockMvc.perform(put("/person/{id}", person1.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                )
                .andExpect(status().isNotFound());
        }
        Mockito.verify(personService, Mockito.times(1))
            .update(person1.getId(), person1);
    }

    @Test
    public void deletePersonTest_whenEmpty() throws Exception {
        int id = 55;
        Optional<Person> optionalPerson = personService.findById(id);
        if (optionalPerson.isEmpty()) {
            mockMvc.perform(delete("/person/{id}", id))
                .andExpect(status().isNotFound());
        }

    }
}
