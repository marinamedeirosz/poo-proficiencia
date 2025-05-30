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

import com.marina.dao.PatientDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.factory.PersonFactory;
import com.marina.model.Patient;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;

@DisplayName("Testes para PatientService")
public class PatientServiceTest {

    private static final String CPF = "13177016402";
    private static final String NAME = "John Doe";
    private static final String PHONE = "123456789";

    private Patient mockPatient;
    final PatientService patientService = new PatientService();


    @BeforeEach
    void setup() {
        mockPatient = new Patient();
        mockPatient.setCpf(CPF);
        mockPatient.setName(NAME);
        mockPatient.setPhone(PHONE);
    }

    @Test
    @DisplayName("Deve criar um paciente com sucesso")
    void testCreatePatient() throws IOException {
        try (
            MockedStatic<ReadValues> readValuesMock = mockStatic(ReadValues.class);
            MockedStatic<PersonFactory> personFactoryMock = mockStatic(PersonFactory.class);
            MockedStatic<PatientDao> patientDaoMock = mockStatic(PatientDao.class)
        ) {
            readValuesMock.when(() -> ReadValues.readName(anyString())).thenReturn(NAME);
            readValuesMock.when(() -> ReadValues.readCpf(anyString())).thenReturn(CPF);
            readValuesMock.when(() -> ReadValues.readPhone(anyString())).thenReturn(PHONE);

            personFactoryMock.when(() ->
                PersonFactory.createPerson(NAME, CPF, PHONE, Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM, null)
            ).thenReturn(mockPatient);

            patientDaoMock.when(() -> PatientDao.createPatient(mockPatient)).thenReturn("Paciente criado com sucesso!");

            assertDoesNotThrow(patientService::createPatient);
        }
    }   

    @Test
    @DisplayName("Deve listar pacientes com sucesso")
    void testListPatients() throws IOException {
        String jsonMock = "[{\"cpf\":\"12345678901\",\"name\":\"John Doe\",\"phone\":\"123456789\"}]";
        try (
            MockedStatic<PatientDao> daoMock = mockStatic(PatientDao.class);
            MockedStatic<JsonParser> parserMock = mockStatic(JsonParser.class)
        ) {
            daoMock.when(PatientDao::listPatients).thenReturn(jsonMock);
            parserMock.when(() -> JsonParser.parseJson(jsonMock, Patient.class)).thenReturn(Arrays.asList(mockPatient));

            List<Patient> patients = patientService.listPatients();

            assertNotNull(patients);
            assertEquals(1, patients.size());
            assertEquals(CPF, patients.get(0).getCpf());
        }
    }
}
