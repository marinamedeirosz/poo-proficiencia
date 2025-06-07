package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.interfaces.DoctorDao;
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

    private final DoctorDao doctorDao;
    private final SpecialtyService specialtyService;

    public DoctorService(DoctorDao doctorDao, SpecialtyService specialtyService) {
        this.doctorDao = doctorDao;
        this.specialtyService = specialtyService;
    }

    public void createDoctor() throws IOException {
        String name = ReadValues.readName("Digite o nome do médico: ");
        String cpf = ReadValues.readCpf("Digite o CPF do médico: ");
        String phone = ReadValues.readPhone("Digite o telefone do médico: ");
        String crm = ReadValues.readCrm("Digite o CRM do médico: ");

        Doctor doctor = (Doctor) PersonFactory.createPerson(name, cpf, phone, Profile.MEDICO, Status.ATIVO, YesOrNo.SIM, crm);
        try {
            String response = doctorDao.createDoctor(doctor);
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
            String json = doctorDao.getDoctor(crm);
            System.out.println(json);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar médico: " + e.getMessage());
        }
    }

    public void listDoctorsView() throws IOException {
        try {
            String json = doctorDao.listDoctors();
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
            String json = doctorDao.listDoctors();
            return JsonParser.parseJson(json, Doctor.class);
        } catch (IOException e) {
            throw new IOException("Erro ao listar médicos: " + e.getMessage());
        }
    }

    public void addSpecialty() throws IOException {
        List<Doctor> doctors = listDoctors();
        listDoctorsView();

        String doctorCrm;
        while (true) {
            String tempCrm = ReadValues.readCrm("Digite o CRM do médico: ");
            boolean validCrm = doctors.stream().anyMatch(d -> d.getCrm().equals(tempCrm) && d.getStatus() == Status.ATIVO);
            if (validCrm) {
                doctorCrm = tempCrm;
                break;
            }
            System.out.println("CRM inválido. Escolha um médico válido e ativo.");
        }

        List<Specialty> specialties = specialtyService.listSpecialties();
        if (specialties.isEmpty()) {
            Style.printLine(50);
            System.out.println("Não existem especialidades cadastradas.");
            Style.printLine(50);
            return;
        }

        int number = 1;
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
            System.out.println(doctorDao.addSpecialty(doctorCrm, specialty.getId(), level));
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public List<Specialty> listSpecialties() throws IOException {
        List<Doctor> doctors = listDoctors();
        listDoctorsView();

        String doctorCrm;
        while (true) {
            String tempCrm = ReadValues.readCrm("Digite o CRM do médico: ");
            boolean validCrm = doctors.stream().anyMatch(d -> d.getCrm().equals(tempCrm) && d.getStatus() == Status.ATIVO);
            if (validCrm) {
                doctorCrm = tempCrm;
                break;
            }
            System.out.println("CRM inválido. Escolha um médico válido e ativo.");
        }

        try {
            String json = doctorDao.listSpecialties(doctorCrm);
            return JsonParser.parseJson(json, Specialty.class);
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }

    public void listSpecialtiesView() throws IOException {
        try {
            List<Specialty> specialties = listSpecialties();

            if (specialties.isEmpty()) {
                System.out.println("Não existem especialidades cadastradas.");
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
