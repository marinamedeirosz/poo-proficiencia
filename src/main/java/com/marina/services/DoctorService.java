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
import com.marina.utils.Style;

public class DoctorService {
    public static void createDoctor() throws IOException {
        String name = ReadValues.readName("Digite o nome do médico: ");
        String cpf = ReadValues.readCpf("Digite o CPF do médico: ");
        String phone = ReadValues.readPhone("Digite o telefone do médico: ");
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
    
        Doctor doctor = (Doctor) PersonFactory.createPerson(name, cpf, phone, Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, crm);
        try {
            String response = DoctorDao.createDoctor(doctor);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
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

    public static void listDoctorsView() throws IOException {
        try {
            String json = DoctorDao.listDoctors();
            List<Doctor> doctors = JsonParser.parseJson(json, Doctor.class);

            if (doctors.isEmpty()) {
                System.out.println("Não existem médicos cadastrados.");
                return;
            }

            for (Doctor d : doctors) {
                System.out.println(d.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar médicos: " + e.getMessage());
        }
    }

    public static List<Doctor> listDoctors() throws IOException {
        try {
            String json = DoctorDao.listDoctors();
            List<Doctor> doctors = JsonParser.parseJson(json, Doctor.class);

            return doctors;
        } catch (IOException e) {
            throw new IOException("Erro ao listar médicos: " + e.getMessage());
        }
    }
}
