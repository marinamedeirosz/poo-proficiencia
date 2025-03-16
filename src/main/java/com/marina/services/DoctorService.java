package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.DoctorDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.factory.PersonFactory;
import com.marina.model.Doctor;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;

public class DoctorService {
    public static void createDoctor() throws IOException {
        String name = ReadValues.readName("Digite o nome do médico: ");
        String cpf = ReadValues.readCpf("Digite o CPF do médico: ");
        String phone = ReadValues.readPhone("Digite o telefone do médico: ");
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
    
        Doctor doctor = (Doctor) PersonFactory.createPerson(name, cpf, phone, Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, crm);
        try {
            DoctorDao.createDoctor(doctor);
        } catch (IOException e) {
            throw new IOException("Erro ao criar médico: " + e.getMessage());
        }
    }

    public static void getDoctor() throws IOException {
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
        try {
            DoctorDao.getDoctor(crm);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar médico: " + e.getMessage());
        }
    }

    public static void updateDoctor() throws IOException {  
        String name = ReadValues.readName("Digite o nome do médico: ");
        String cpf = ReadValues.readCpf("Digite o CPF do médico: ");
        String phone = ReadValues.readPhone("Digite o telefone do médico: ");
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
        
        Doctor doctor = (Doctor) PersonFactory.createPerson(name, cpf, phone, Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, crm);
        try {
            DoctorDao.updateDoctor(doctor);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar médico: " + e.getMessage());
        }
    }

    public static void deleteDoctor() throws IOException {
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
        try {
            DoctorDao.deleteDoctor(crm);
        } catch (IOException e) {
            throw new IOException("Erro ao deletar médico: " + e.getMessage());
        }
    }

    public static void listDoctors() throws IOException {
        try {
            String json = DoctorDao.listDoctors();
            List<Doctor> doctors = JsonParser.parseJson(json, Doctor.class);

            for (Doctor d : doctors) {
                System.out.println(d.toString());
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar médicos: " + e.getMessage());
        }
    }
}
