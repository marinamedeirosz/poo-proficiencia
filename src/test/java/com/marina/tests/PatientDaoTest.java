package com.marina.tests;

import com.marina.dao.impl.PatientDaoImpl;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Patient;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientDaoTest {

    private static final String TEST_USER_CPF = "12435035306";

    private PatientDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new PatientDaoImpl();
    }

    @Test
    @Order(1)
    void shouldCreatePatient() {
        final Patient patient = new Patient("Test User", TEST_USER_CPF, "82999999999", Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM);

        Assertions.assertDoesNotThrow(() -> dao.createPatient(patient));
    }

    @Test
    @Order(2)
    void shouldFailCreatePatient() throws IOException {
        final Patient patient = new Patient("Test User", TEST_USER_CPF, "82999999999", Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM);

        Assertions.assertEquals("CPF já cadastrados no sistema.", dao.createPatient(patient));
    }

    @Test
    @Order(3)
    void shouldListPatients() {
        final String patientsJson = Assertions.assertDoesNotThrow(dao::listPatients);

        Assertions.assertTrue(patientsJson.contains(TEST_USER_CPF));
    }

    @Test
    @Order(4)
    void shouldUpdatePatient() {
        final Patient patient = new Patient("Test User", TEST_USER_CPF, "82999999999", Profile.PACIENTE, Status.INATIVO, YesOrNo.SIM);

        Assertions.assertDoesNotThrow(() -> dao.updatePatient(patient));
    }

    @Test
    @Order(6)
    void shouldDeletePatient()  {
        Assertions.assertDoesNotThrow(() -> dao.deletePatient(TEST_USER_CPF));
    }

}
