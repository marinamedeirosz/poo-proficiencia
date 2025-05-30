package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.DoctorDao;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.factory.PersonFactory;
import com.marina.model.Doctor;
import com.marina.model.Specialty;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class DoctorService {
    public void createDoctor() throws IOException {
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

    public void getDoctor() throws IOException {
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");
        try {
            DoctorDao.getDoctor(crm);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar médico: " + e.getMessage());
        }
    }

    public void listDoctorsView() throws IOException {
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

    public List<Doctor> listDoctors() throws IOException {
        try {
            String json = DoctorDao.listDoctors();
            List<Doctor> doctors = JsonParser.parseJson(json, Doctor.class);

            return doctors;
        } catch (IOException e) {
            throw new IOException("Erro ao listar médicos: " + e.getMessage());
        }
    }

    public void addSpecialty() throws IOException {
        final String doctorCrm;

        final DoctorService doctorService = new DoctorService();
        final SpecialtyService specialtyService = new SpecialtyService();

        List<Doctor> doctors = doctorService.listDoctors();
        doctorService.listDoctorsView();
        while (true) {
            String tempCrm = ReadValues.readCrm("Digite o CRM do médico: ");
            if (doctors.stream().anyMatch(d -> d.getCrm().equals(tempCrm)) && doctors.stream().anyMatch(d -> d.getStatus() == Status.ATIVO)) {
                doctorCrm = tempCrm;
                break;
            }
            System.out.println("CRM inválido. Escolha um médico válido e ativo.");
        }

        List<Specialty> specialties = specialtyService.listSpecialties();
        int number = 1;

        if (specialties.isEmpty()) {
            Style.printLine(50);
            System.out.println("Não existem especialidades cadastradas.");
            Style.printLine(50);
            return;
        }

        for (Specialty specialty : specialties) {
            System.out.println("[ " + number + " ] - " + specialty.toString());
            Style.printLine(50);
            number++;
        }

        int option = ReadValues.readMenuOption("Digite o número da especialidade que deseja adicionar: ", 1, specialties.size());
        Specialty specialty = specialties.get(option - 1);

        int level = ReadValues.readMenuOption("Digite o nível de prificiência do médico na especialidade (entre 1 e 5): ", 1, 5);

        try {
            Style.printLine(50);
            System.out.println(DoctorDao.addSpecialty(doctorCrm, specialty.getId(), level));
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar consulta: " + e.getMessage());
        }        
    } 

    public List<Specialty> listSpecialties() throws IOException {
        final String doctorCrm;

        final DoctorService doctorService = new DoctorService();

        List<Doctor> doctors = doctorService.listDoctors();
        doctorService.listDoctorsView();
        while (true) {
            String tempCrm = ReadValues.readCrm("Digite o CRM do médico: ");
            if (doctors.stream().anyMatch(d -> d.getCrm().equals(tempCrm)) && doctors.stream().anyMatch(d -> d.getStatus() == Status.ATIVO)) {
                doctorCrm = tempCrm;
                break;
            }
            System.out.println("CRM inválido. Escolha um médico válido e ativo.");
        }

        try {
            String json = DoctorDao.listSpecialties(doctorCrm);
            List<Specialty> specialties = JsonParser.parseJson(json, Specialty.class);

            return specialties;
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }

    public void listSpecialtiesView() throws IOException {
        try {
            List<Specialty> specialties = listSpecialties();

            if (specialties.isEmpty()) {
                System.out.println("Não existem especialidades cadastrados.");
                return;
            }

            Style.printLine(50);
            for (Specialty s : specialties) {
                System.out.println(s.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }
}
