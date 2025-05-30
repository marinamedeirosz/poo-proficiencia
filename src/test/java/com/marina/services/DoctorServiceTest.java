package com.marina.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;

import com.marina.dao.DoctorDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.factory.PersonFactory;
import com.marina.model.Doctor;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;

@DisplayName("Testes para DoctorService")
public class DoctorServiceTest {

    private static final String CPF = "13177016402";
    private static final String NAME = "John Doe";
    private static final String PHONE = "123456789";
    private static final String CRM = "123456";

    private Doctor mockDoctor;
    final DoctorService doctorService = new DoctorService();


    @BeforeEach
    void setup() {
        mockDoctor = new Doctor();
        mockDoctor.setCpf(CPF);
        mockDoctor.setName(NAME);
        mockDoctor.setPhone(PHONE);
        mockDoctor.setCrm(CRM);
    }

    @Test
    @DisplayName("Deve criar um Médico com sucesso")
    void testCreateDoctor() throws IOException {
        try (
            MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class);
            MockedStatic<PersonFactory> personFactoryMock = mockStatic(PersonFactory.class);
            MockedStatic<DoctorDao> DoctorDaoMock = mockStatic(DoctorDao.class)
        ) {
            readValuesMock.when(() -> ReadValues.readName(anyString())).thenReturn(NAME);
            readValuesMock.when(() -> ReadValues.readCpf(anyString())).thenReturn(CPF);
            readValuesMock.when(() -> ReadValues.readPhone(anyString())).thenReturn(PHONE);
            readValuesMock.when(() -> ReadValues.readCrm(anyString())).thenReturn(CRM);

            personFactoryMock.when(() ->
                PersonFactory.createPerson(NAME, CPF, PHONE, Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, CRM)
            ).thenReturn(mockDoctor);

            DoctorDaoMock.when(() -> DoctorDao.createDoctor(mockDoctor)).thenReturn("Médico criado com sucesso!");

            assertDoesNotThrow(doctorService::createDoctor);
        }
    }   

    @Test
    @DisplayName("Deve listar Médicos com sucesso")
    void testListDoctors() throws IOException {
        String jsonMock = "[{\"cpf\":\"12345678901\",\"name\":\"John Doe\",\"phone\":\"123456789\",\"crm\":\"123456\"}]";
        try (
            MockedStatic<DoctorDao> daoMock = mockStatic(DoctorDao.class);
            MockedStatic<JsonParser> parserMock = mockStatic(JsonParser.class)
        ) {
            daoMock.when(DoctorDao::listDoctors).thenReturn(jsonMock);
            parserMock.when(() -> JsonParser.parseJson(jsonMock, Doctor.class)).thenReturn(Arrays.asList(mockDoctor));

            List<Doctor> Doctors = doctorService.listDoctors();

            assertNotNull(Doctors);
            assertEquals(1, Doctors.size());
            assertEquals(CPF, Doctors.get(0).getCpf());
        }
    }
}
