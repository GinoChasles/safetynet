package com.safety.safetynet.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynet.SafetynetApplication;
import com.safety.safetynet.model.Person;
import org.junit.jupiter.api.Test;
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
}
