package com.safety.safetynet.service;

import com.safety.safetynet.model.Allergies;
import com.safety.safetynet.model.MedicalRecords;
import com.safety.safetynet.model.Medications;
import com.safety.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordService;

    private MedicalRecords medicalRecords;
    private List<Medications> medications;
    private Set<Allergies> allergies;
    private Allergies allergies1;
    private Medications medications1;
    private LocalDate birthday;

    @BeforeEach
    void setUp() {
        medications = new ArrayList<>();
        allergies = new HashSet<>();
        allergies1 = new Allergies();
        allergies1.setAllergies("nillacilan");
        medications1 = new Medications();
        medications1.setMedication("hydrapermazol:100mg");
        medications.add(medications1);
        allergies.add(allergies1);
        birthday = LocalDate.of(2012,12,12);

        medicalRecords = new MedicalRecords();
        medicalRecords.setId(1);
        medicalRecords.setLastName("TestLastName");
        medicalRecords.setFirstName("TestFirstName");
        medicalRecords.setMedications(medications);
        medicalRecords.setAllergies(allergies);
        medicalRecords.setBirthdate(birthday);
    }

    @Test
    public void findById() {
        when(medicalRecordRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(medicalRecords));

        Optional<MedicalRecords> medicalRecordsOptional = medicalRecordService.findById(1);
        MedicalRecords medicalRecords = new MedicalRecords();
        if(medicalRecordsOptional.isPresent()){
            medicalRecords = medicalRecordsOptional.get();
        }
        assertThat(medicalRecords.getFirstName()).isEqualTo("TestFirstName");

        verify(medicalRecordRepository, times(1)).findById(Mockito.anyInt());
    }

    @Test
    public void insertTest() {
        when(medicalRecordRepository.save(Mockito.any(MedicalRecords.class))).thenReturn(medicalRecords);

        MedicalRecords medicalRecordsSave = medicalRecordService.insert(medicalRecords);

        assertThat(medicalRecordsSave.getId()).isNotNull();
        assertThat(medicalRecordsSave.getFirstName()).isEqualTo("TestFirstName");
    }

//    @Test
//    public void deleteTest() {
//        ArgumentCaptor<MedicalRecords> argument = ArgumentCaptor.forClass(MedicalRecords.class);
//
//        medicalRecordService.delete(medicalRecords.getId());
////        verify(medicalRecordRepository, times(1)).delete(medicalRecords);
//        Mockito.verify(medicalRecordRepository, Mockito.times(1)).delete(argument.capture());
//
//    }

    @Test
    public void updateTest() {

    }

//    @Test
//    public void deleteByNameTest() {
//
//    }

    @Test
    public void findAllTest() {
        List<MedicalRecords> medicalRecordsList = new ArrayList<MedicalRecords>();
        medicalRecordsList.add(medicalRecords);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordsList);

        Iterable<MedicalRecords> medicalRecordsList1 = medicalRecordService.findAll();
        MedicalRecords medicalRecords1 = medicalRecordsList1.iterator().next();

        assertThat(medicalRecords1.getFirstName()).isEqualTo("TestFirstName");

        verify(medicalRecordRepository, times(1)).findAll();
    }
}
