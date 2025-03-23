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

import com.marina.dao.DoctorDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Doctor;
import com.marina.utils.ReadValues;

public class DoctorServiceTest {
    
    @Mock
    private DoctorDao doctorDao;

    private Doctor testDoctor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testDoctor = new Doctor(
            "John Doe",
            "54055874006",
            "11999999999",
            Profile.MEDICO,
            Status.ATIVO,
            YesOrNo.SIM,
            "134679"
        );
    }

    @Test
    public void testCreateDoctor_Success() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readCpf(anyString())).thenReturn("12345678901");
        when(ReadValues.readPhone(anyString())).thenReturn("11999999999");
        when(ReadValues.readCrm(anyString())).thenReturn("123456");
        when(DoctorDao.createDoctor(any(Doctor.class))).thenReturn("Médico criado com sucesso!");

        DoctorService.createDoctor();

        verify(doctorDao);
        DoctorDao.createDoctor(any(Doctor.class));
    }

    @Test(expected = IOException.class)
    public void testCreateDoctor_ThrowsIOException() throws IOException {
        when(ReadValues.readName(anyString())).thenReturn("John Doe");
        when(ReadValues.readCpf(anyString())).thenReturn("12345678901");
        when(ReadValues.readPhone(anyString())).thenReturn("11999999999");
        when(ReadValues.readCrm(anyString())).thenReturn("123456");
        when(DoctorDao.createDoctor(any(Doctor.class))).thenThrow(new IOException("Connection error"));

        DoctorService.createDoctor();
    }

    @Test
    public void testListDoctors_Success() throws IOException {
        List<Doctor> expectedDoctors = Arrays.asList(testDoctor);
        when(DoctorService.listDoctors()).thenReturn(expectedDoctors);

        List<Doctor> result = DoctorService.listDoctors();

        assertNotNull(result);
        assertEquals(1, result.size());
        Doctor resultDoctor = result.get(0);
        assertEquals(testDoctor.getName(), resultDoctor.getName());
        assertEquals(testDoctor.getCpf(), resultDoctor.getCpf());
        verify(doctorDao);
        DoctorDao.listDoctors();
    }
}
