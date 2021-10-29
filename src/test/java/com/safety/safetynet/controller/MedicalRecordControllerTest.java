package com.safety.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynet.SafetynetApplication;
import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = SafetynetApplication.class)
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    @Order(1)
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/medicalrecord"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/medicalrecord/1"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void addMedicalRecordTest() throws Exception {
        List<Medications> medications = new ArrayList<>();
        Set<Allergies> allergies = new HashSet<>();
        LocalDate localDate = LocalDate.now();
        MedicalRecords medicalRecords = new MedicalRecords
                (25, "Test", "Test", localDate,
                        medications, allergies);
        String json = mapper.writeValueAsString(medicalRecords);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalrecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void updateTest() throws Exception {
        List<Medications> medications = new ArrayList<>();
        Set<Allergies> allergies = new HashSet<>();
        LocalDate localDate = LocalDate.now();
        MedicalRecords medicalRecords = new MedicalRecords
                (2, "TEST2", "test2", localDate,
                        medications, allergies);
        String json = mapper.writeValueAsString(medicalRecords);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/medicalrecord/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/medicalrecord/1"))
                .andExpect(status().isAccepted());
    }
}
