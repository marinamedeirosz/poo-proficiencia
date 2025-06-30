package com.marina.tests;

import com.marina.dao.impl.DoctorDaoImpl;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Doctor;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorDaoTest {

    private static final String TEST_USER_CPF = "85504279186";
    private static final String TEST_USER_CRM = "111222";

    private DoctorDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new DoctorDaoImpl();
    }

    @Test
    @Order(1)
    void shouldCreateDoctor() {
        final Doctor doctor = new Doctor("Test Doctor", TEST_USER_CPF, "82999999999", Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, TEST_USER_CRM);

        Assertions.assertDoesNotThrow(() -> dao.createDoctor(doctor));
    }

    @Test
    @Order(2)
    void shouldFailCreateDoctor() throws IOException {
        final Doctor doctor = new Doctor("Test Doctor", TEST_USER_CPF, "82999999999", Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, TEST_USER_CRM);

        Assertions.assertEquals("CPF já cadastrado no sistema.", dao.createDoctor(doctor));
    }

    @Test
    @Order(3)
    void shouldListDoctor() {
        final String list = Assertions.assertDoesNotThrow(() -> dao.listDoctors());

        Assertions.assertTrue(list.contains(TEST_USER_CRM));
    }

    @Test
    @Order(4)
    void shouldDeleteDoctor() {
        Assertions.assertDoesNotThrow(() -> dao.deleteDoctor(TEST_USER_CRM));
    }

}
