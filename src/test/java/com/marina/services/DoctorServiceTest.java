package com.marina.services;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.marina.dao.interfaces.DoctorDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.model.Doctor;
import com.marina.model.Specialty;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;

class DoctorServiceTest {

    private DoctorDao doctorDao;
    private SpecialtyService specialtyService;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorDao = mock(DoctorDao.class);
        specialtyService = mock(SpecialtyService.class);
        doctorService = new DoctorService(doctorDao, specialtyService);
    }

    @Test
    void shouldReturnListOfDoctors() throws IOException {
        String json = """
            [
                {"name":"Dr. Alice", "cpf":"123", "phone":"9999", "crm":"CR001", "status":"ATIVO"}
            ]
        """;

        when(doctorDao.listDoctors()).thenReturn(json);

        List<Doctor> doctors = doctorService.listDoctors();

        assertEquals(1, doctors.size());
        assertEquals("Dr. Alice", doctors.get(0).getName());
    }

    @Test
    void shouldCreateDoctorSuccessfully() throws IOException {
        try (
            MockedStatic<ReadValues> readValues = mockStatic(ReadValues.class);
        ) {
            readValues.when(() -> ReadValues.readName(anyString())).thenReturn("Dr. Alice");
            readValues.when(() -> ReadValues.readCpf(anyString())).thenReturn("123");
            readValues.when(() -> ReadValues.readPhone(anyString())).thenReturn("9999");
            readValues.when(() -> ReadValues.readCrm(anyString())).thenReturn("CR001");

            when(doctorDao.createDoctor(any(Doctor.class))).thenReturn("Médico criado com sucesso");

            doctorService.createDoctor();

            verify(doctorDao, times(1)).createDoctor(any(Doctor.class));
        }
    }

    @Test
    void shouldListSpecialtiesForDoctor() throws IOException {
        String crm = "CR001";
        Doctor activeDoctor = new Doctor("Dr. Bob", "123", "999", Profile.MEDICO, Status.ATIVO, YesOrNo.NAO, crm);

        when(doctorDao.listDoctors()).thenReturn("[{}]");
        try (
            MockedStatic<ReadValues> mockedStatic = mockStatic(ReadValues.class);
            MockedStatic<JsonParser> jsonParser = mockStatic(JsonParser.class)
        ) {
            jsonParser.when(() -> JsonParser.parseJson(anyString(), eq(Doctor.class))).thenReturn(List.of(activeDoctor));
            mockedStatic.when(() -> ReadValues.readCrm(anyString())).thenReturn(crm);

            when(doctorDao.listSpecialties(crm)).thenReturn("[]");
            jsonParser.when(() -> JsonParser.parseJson(anyString(), eq(Specialty.class))).thenReturn(List.of());

            List<Specialty> result = doctorService.listSpecialties();
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void shouldThrowExceptionWhenDoctorDaoFails() throws IOException {
        when(doctorDao.listDoctors()).thenThrow(new IOException("Erro no DAO"));

        IOException ex = assertThrows(IOException.class, () -> {
            doctorService.listDoctors();
        });

        assertEquals("Erro ao listar médicos: Erro no DAO", ex.getMessage());
    }
}
