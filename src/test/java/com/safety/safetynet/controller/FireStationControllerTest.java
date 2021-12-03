package com.safety.safetynet.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynet.SafetynetApplication;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.service.FireStationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
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
    FireStation fireStation = new FireStation(14, "test", 1);
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
    FireStation fireStation = new FireStation(14, "test", 2);
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

  @Test
  public void getOneFireStationByIdTest_whenEmpty() throws Exception {

    Optional<FireStation> fireStation = fireStationService.findById(100);
    if (fireStation.isEmpty()) {
      mockMvc.perform(get("/firestation/{id}", 100))
          .andExpect(status().isNotFound());
    }
    Mockito.verify(fireStationService, Mockito.times(1)).findById(100);
  }

  @Test
  public void updateFireStationTest_whenEmpty() throws Exception {
    FireStation fireStationToUpdate = new FireStation(15, "rue bidon", 1);
    String json = mapper.writeValueAsString(fireStationToUpdate);

    FireStation fireStation =
        fireStationService.update(fireStationToUpdate.getId(), fireStationToUpdate);
    if (fireStation == null) {
      mockMvc.perform(put("/firestation/{id}", fireStationToUpdate.getId())
              .contentType(MediaType.APPLICATION_JSON)
              .content(json)
          )
          .andExpect(status().isNotFound());
    }
    Mockito.verify(fireStationService, Mockito.times(1))
        .update(fireStationToUpdate.getId(), fireStationToUpdate);
  }

  @Test
  public void deleteFireStationTest_whenEmpty() throws Exception {
    int id = 55;
    Optional<FireStation> fireStationToDelete = fireStationService.findById(id);
    if (fireStationToDelete.isEmpty()) {
      mockMvc.perform(delete("/firestation/{id}", id))
          .andExpect(status().isNotFound());
    }

  }

//  @Test
//  public void getPersonByStationNumberTest_whenEmpty() throws Exception {
//    int stationNumber = 50;
//    FireStationCoverage result;
//    Mockito.when(fireStationService.findAllByFireStationNumber(stationNumber)).thenReturn(result = new FireStationCoverage());
//    if (result.getPersonInfosList().isEmpty()) {
//      mockMvc.perform(get("/firestation?stationNumber={id}", stationNumber))
//          .andExpect(status().isNotFound());
//    }
//  }


}
