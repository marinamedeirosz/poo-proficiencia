package com.marina.tests;

import com.marina.dao.impl.AppointmentDaoImpl;
import com.marina.dao.impl.DoctorDaoImpl;
import com.marina.dao.impl.PatientDaoImpl;
import com.marina.enums.AppointmentStatus;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Appointment;
import com.marina.model.Doctor;
import com.marina.model.Patient;
import org.junit.jupiter.api.*;

import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentDaoTest {

    private static final String TEST_DOCTOR_CPF = "85504279186";
    private static final String TEST_DOCTOR_CRM = "111222";
    private static final String TEST_PATIENT_CPF = "12435035306";

    private AppointmentDaoImpl dao;
    private DoctorDaoImpl doctorDao;
    private PatientDaoImpl patientDao;

    @BeforeEach
    void setUp() {
        dao = new AppointmentDaoImpl();
        doctorDao = new DoctorDaoImpl();
        patientDao = new PatientDaoImpl();
    }

    @Test
    @Order(1)
    void createDoctor() {
        final Doctor doctor = new Doctor("Test Doctor", TEST_DOCTOR_CPF, "82999999999", Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, TEST_DOCTOR_CRM);

        Assertions.assertDoesNotThrow(() -> doctorDao.createDoctor(doctor));
    }

    @Test
    @Order(2)
    void createPatient() {
        final Patient patient = new Patient("Test User", TEST_PATIENT_CPF, "82999999999", Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM);

        Assertions.assertDoesNotThrow(() -> patientDao.createPatient(patient));
    }

    @Test
    @Order(3)
    void createAppointment() {
        final Appointment appointment = new Appointment(TEST_DOCTOR_CRM, TEST_PATIENT_CPF, new Date(), "TEST APPOINTMENT", AppointmentStatus.REALIZADA, "");

        Assertions.assertDoesNotThrow(() -> Assertions.assertEquals("Consulta criada com sucesso!", dao.createAppointment(appointment)));
    }

    @Test
    @Order(4)
    void cleanup() {
        Assertions.assertDoesNotThrow(() -> doctorDao.deleteDoctor(TEST_DOCTOR_CRM));
        Assertions.assertDoesNotThrow(() -> patientDao.deletePatient(TEST_PATIENT_CPF));
    }

}
