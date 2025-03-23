package com.marina.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.marina.dao.PatientDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Patient;
import com.marina.utils.ReadValues;

public class PatientServiceTest {
    @Mock
    private PatientDao patientDao;

    private Patient testPatient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testPatient = new Patient(
            "John Doe",
            "54055874006",
            "11999999999",
            Profile.PACIENTE,
            Status.ATIVO,
            YesOrNo.SIM
        );
    }

    @Test
    public void testCreatePatient_Success() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readCpf(anyString())).thenReturn("54055874006");
        when(ReadValues.readPhone(anyString())).thenReturn("11999999999");
        when(PatientDao.createPatient(any(Patient.class))).thenReturn("Paciente criado com sucesso!");

        PatientService.createPatient();

        verify(patientDao);
        PatientDao.createPatient(any(Patient.class));
    }

    @Test(expected = IOException.class)
    public void testCreatePatient_ThrowsIOException() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readCpf(anyString())).thenReturn("54055874006");
        when(ReadValues.readPhone(anyString())).thenReturn("11999999999");
        when(PatientDao.createPatient(any(Patient.class))).thenThrow(new IOException("Connection error"));

        PatientService.createPatient();
    }

    @Test
    public void testListPatients_Success() throws IOException {
        List<Patient> expectedPatients = Arrays.asList(testPatient);
        when(PatientService.listPatients()).thenReturn(expectedPatients);

        List<Patient> result = PatientService.listPatients();

        assertNotNull(result);
        assertEquals(1, result.size());
        Patient resultPatient = result.get(0);
        assertEquals(testPatient.getName(), resultPatient.getName());
        assertEquals(testPatient.getCpf(), resultPatient.getCpf());
        verify(patientDao);
        PatientDao.listPatients();
    }
}
