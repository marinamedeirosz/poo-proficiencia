package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.PatientDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.factory.PersonFactory;
import com.marina.model.Patient;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;

public class PatientService {
    public static void createPatient() throws IOException {
        String name = ReadValues.readName("Digite o nome do paciente: ");
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        String phone = ReadValues.readPhone("Digite o telefone do paciente: ");
    
        Patient patient = (Patient) PersonFactory.createPerson(name, cpf, phone, Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM, null);
        try {
                PatientDao.createPatient(patient);
        } catch (IOException e) {
            throw new IOException("Erro ao criar paciente: " + e.getMessage());
        }
    }

    public static void getPatient() throws IOException {
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        try {
            PatientDao.getPatient(cpf);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar paciente: " + e.getMessage());
        }
    }

    public static void updatePatient() throws IOException {
        String name = ReadValues.readName("Digite o nome do paciente: ");
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        String phone = ReadValues.readPhone("Digite o telefone do paciente: ");
        Patient patient = (Patient) PersonFactory.createPerson(name, cpf, phone, Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM, null);
        try {
            PatientDao.updatePatient(patient);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public static void deletePatient() throws IOException {
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        try {
            PatientDao.deletePatient(cpf);
        } catch (IOException e) {
            throw new IOException("Erro ao deletar paciente: " + e.getMessage());
        }   
    }

    public static void listPatients() throws IOException {
        try {
            String json = PatientDao.listPatients();
            List<Patient> patients = JsonParser.parseJson(json, Patient.class);

            for (Patient p : patients) {
                System.out.println(p.toString());
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar pacientes: " + e.getMessage());
        }
    }
}
