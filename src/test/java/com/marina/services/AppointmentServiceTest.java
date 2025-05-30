package com.marina.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.marina.dao.AppointmentDao;
import com.marina.enums.AppointmentStatus;
import com.marina.model.Appointment;
import com.marina.utils.ReadValues;

public class AppointmentServiceTest {
    @Mock
    private AppointmentDao appointmentDao;

    private Appointment testAppointment;
    private String testDoctor;
    private String testPatient;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testDoctor = "Dr Smith";
        testPatient = "John Doe";
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
    public void testCreateAppointment_Success() throws IOException {
        when(ReadValues.readDate(anyString())).thenReturn(new Date());
        when(ReadValues.readMenuOption(anyString(), 0, anyInt())).thenReturn(1);
        when(AppointmentDao.createAppointment(any(Appointment.class))).thenReturn("Consulta criada com sucesso!");

        AppointmentService.createAppointment();

        verify(appointmentDao);
        AppointmentDao.createAppointment(testAppointment);
    }

    @Test
    public void testCreateAppointment_Failed() throws IOException {
        when(ReadValues.readDate(anyString())).thenReturn(new Date());
        when(ReadValues.readMenuOption(anyString(), 0, anyInt())).thenReturn(1);
        when(AppointmentDao.createAppointment(any(Appointment.class))).thenThrow(new IOException("Creation failed"));

        AppointmentService.createAppointment();
    }

    @Test
    public void testListAppointments_Success() throws IOException {
        List<Appointment> expectedAppointments = Arrays.asList(testAppointment);

        when(AppointmentService.listAppointments()).thenReturn(expectedAppointments);

        List<Appointment> result = AppointmentService.listAppointments();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(appointmentDao);
    }

    @Test
    public void testUpdateAppointment_Success() throws IOException {
        when(ReadValues.readDate(anyString())).thenReturn(new Date());
        when(ReadValues.readMenuOption(anyString(), 0,anyInt())).thenReturn(1);
        doNothing().when(appointmentDao);
        AppointmentDao.updateAppointment(any(Appointment.class));

        AppointmentService.updateAppointment();

        verify(appointmentDao);
        AppointmentDao.updateAppointment(testAppointment);
    }
}
