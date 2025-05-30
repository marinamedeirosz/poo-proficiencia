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
import com.marina.utils.Style;

public class PatientService {
    public void createPatient() throws IOException {
        String name = ReadValues.readName("Digite o nome do paciente: ");
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        String phone = ReadValues.readPhone("Digite o telefone do paciente: ");
    
        Patient patient = (Patient) PersonFactory.createPerson(name, cpf, phone, Profile.PACIENTE, Status.ATIVO, YesOrNo.SIM, null);
        try {
            String response = PatientDao.createPatient(patient);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao criar paciente: " + e.getMessage());
        }
    }

    public void getPatient() throws IOException {
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        try {
            PatientDao.getPatient(cpf);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar paciente: " + e.getMessage());
        }
    }

    public void updatePatient() throws IOException {
        List<Patient> patients = listPatients();
        int number = 1;

        for (Patient patient : patients) {
            System.out.println(number + " - " + patient.toString());
            number++;
        }
        int patientNumber = ReadValues.readMenuOption("Digite o número do paciente: ", 1, number);
        Patient patient = patients.get(patientNumber - 1);

        String name = ReadValues.readName("Digite o nome do paciente: ");
        String phone = ReadValues.readPhone("Digite o telefone do paciente: ");
        Status status = ReadValues.readStatus("Digite a situação do paciente: ");

        Patient patientUpdated = (Patient) PersonFactory.createPerson(name, patient.getCpf(), phone, Profile.PACIENTE, status, YesOrNo.SIM, null);
        try {
            PatientDao.updatePatient(patientUpdated);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

<<<<<<< HEAD
=======
    public void deletePatient() throws IOException {
        String cpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        try {
            PatientDao.deletePatient(cpf);
        } catch (IOException e) {
            throw new IOException("Erro ao deletar paciente: " + e.getMessage());
        }   
    }
>>>>>>> 3ef5cff68c3e3765b1dacc9dd4b44500f9f18e66

    public List<Patient> listPatients() throws IOException {
        try {
            String json = PatientDao.listPatients();
            List<Patient> patients = JsonParser.parseJson(json, Patient.class);

            return patients;
        } catch (IOException e) {
            throw new IOException("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public void listPatientsView() throws IOException {
        try {
            List<Patient> patients = listPatients();

            if (patients.isEmpty()) {
                System.out.println("Não existem pacientes cadastrados.");
                return;
            }

            for (Patient patient : patients) {
                System.out.println(patient.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar pacientes: " + e.getMessage());
        }
    }
}
