package com.safety.safetynet.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynet.SafetynetApplication;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.service.FireStationServiceImpl;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

@SpringBootTest(classes = SafetynetApplication.class)
@AutoConfigureMockMvc
public class FireStationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    FireStationServiceImpl fireStationService;

    @Test
    @Order(1)
    public void getAllFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation"))
        .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getFirstFireStationTest() throws Exception {
        mockMvc.perform(get("/firestation/1")).andExpect(status().isOk())
                .andExpect(jsonPath("address", is("1509 Culver St")));
    }

    @Test
    @Order(3)
    public void postFireStationTest() throws Exception {
        FireStation fireStation = new FireStation(14,"test", 1);
        String json = mapper.writeValueAsString(fireStation);
        MockHttpServletRequestBuilder mock = MockMvcRequestBuilders
                .post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(mock).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void putFireStationTest() throws Exception {
        FireStation fireStation = new FireStation(14,"test", 2);
        String json = mapper.writeValueAsString(fireStation);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/firestation/14")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void deleteFireStationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/firestation/14"))
                .andExpect(status().isAccepted());
    }

    @Test
    @Order(6)
    public void getPersonByStationNumberTest() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void getPhoneAlertTest() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void getFloodTest() throws Exception {
        mockMvc.perform(get("/flood/stations?stationNumber=3"))
                .andExpect(status().isOk());
    }

//    @Test
//    public void getAllFireStationTest_whenEmpty() throws Exception {
////        Mockito.when(fireStationService.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//        Mockito.when(fireStationService.findById(Mockito.anyInt())).thenThrow(new NotFoundException("Not found."));
//
//        mockMvc.perform(get("/firestation/{id}",100)).andExpect(status().isNotFound());
////        Assertions.assertThat(result.getResolvedException().getMessage()).isEqualTo("Not found.");
//        Mockito.verify(fireStationService, Mockito.times(1)).findById(100);
////        Mockito.verifyNoMoreInteractions(fireStationService);
//    }
}
