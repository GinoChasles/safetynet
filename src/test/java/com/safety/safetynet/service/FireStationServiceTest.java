package com.safety.safetynet.service;

import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.repository.FireStationRepository;
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

    @InjectMocks
    private FireStationServiceImpl fireStationService;

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
//        List<FireStation> fireStationList = new ArrayList<>();
//        fireStationList.add(fireStation);
//        fireStationList.add(fireStation);
//
//        Mockito.when(fireStationRepository.findAllByStation(1)).thenReturn(fireStationList);
//        assertThat(fireStationService.findAllByFireStationNumber(1));
//        Mockito.verify(fireStationRepository, Mockito.times(1)).findFireStationByStation(1);
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

