package com.marina.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonParser;
import com.marina.dao.interfaces.AppointmentDao;
import com.marina.enums.AppointmentStatus;
import com.marina.model.Appointment;
import com.marina.utils.ReadValues;

public class AppointmentServiceTest {

    @Mock
    private AppointmentDao appointmentDao;

    private AppointmentService appointmentService;

    private Appointment testAppointment;
    private String testDoctor = "Dr Smith";
    private String testPatient = "John Doe";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        appointmentService = new AppointmentService(appointmentDao); // inject mock

        testAppointment = new Appointment(
            testDoctor,
            testPatient,
            new Date(),
            "observacao",
            AppointmentStatus.AGENDADA,
            "1"
        );
    }

    @Test
    @DisplayName("Should create appointment successfully")
    public void testCreateAppointment_Success() throws IOException {
        try (MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class)) {
            readValuesMock.when(() -> ReadValues.readDate(anyString())).thenReturn(new Date());
            readValuesMock.when(() -> ReadValues.readMenuOption(anyString(), anyInt(), anyInt())).thenReturn(1);

            when(appointmentDao.createAppointment(any(Appointment.class))).thenReturn("Consulta criada com sucesso!");

            String result = appointmentService.createAppointment();

            assertEquals("Consulta criada com sucesso!", result);
            verify(appointmentDao).createAppointment(any(Appointment.class));
        }
    }

    @Test
    @DisplayName("Should handle appointment creation failure")
    public void testCreateAppointment_Failed() throws IOException {
        try (MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class)) {
            readValuesMock.when(() -> ReadValues.readDate(anyString())).thenReturn(new Date());
            readValuesMock.when(() -> ReadValues.readMenuOption(anyString(), anyInt(), anyInt())).thenReturn(1);

            when(appointmentDao.createAppointment(any(Appointment.class))).thenThrow(new IOException("Creation failed"));

            IOException thrown = assertThrows(IOException.class, () -> appointmentService.createAppointment());
            assertEquals("Creation failed", thrown.getMessage());
            verify(appointmentDao).createAppointment(any(Appointment.class));
        }
    }

    @Test
    public void testListAppointments_Success() throws IOException {
        // Mock JSON string returned by DAO
        String jsonMock = "[{\"doctor\":\"Dr Smith\",\"patient\":\"John Doe\",\"date\":\"2025-06-07T10:00:00\",\"observation\":\"observacao\",\"status\":\"A\",\"id\":\"1\"}]";
    
        // Mock DAO to return JSON string
        when(appointmentDao.listAppointments()).thenReturn(jsonMock);
    
        // Call service method which will parse JSON internally
        List<Appointment> result = appointmentService.listAppointments();
    
        // Validate result
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(testAppointment.getDoctor(), result.get(0).getDoctor());
    
        // Verify DAO was called
        verify(appointmentDao).listAppointments();
    }
    
    

    @Test
    @DisplayName("Should update appointment successfully")
    public void testUpdateAppointment_Success() throws IOException {
        try (MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class)) {
            readValuesMock.when(() -> ReadValues.readDate(anyString())).thenReturn(new Date());
            readValuesMock.when(() -> ReadValues.readMenuOption(anyString(), anyInt(), anyInt())).thenReturn(1);

            doNothing().when(appointmentDao).updateAppointment(any(Appointment.class));

            appointmentService.updateAppointment();

            verify(appointmentDao).updateAppointment(any(Appointment.class));
        }
    }
}
