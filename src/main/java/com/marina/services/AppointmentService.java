package com.marina.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.marina.dao.interfaces.AppointmentDao;
import com.marina.enums.AppointmentStatus;
import com.marina.enums.Status;
import com.marina.model.Appointment;
import com.marina.model.Doctor;
import com.marina.model.Patient;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class AppointmentService {

    private final AppointmentDao appointmentDao;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentService(AppointmentDao appointmentDao, DoctorService doctorService, PatientService patientService) {
        this.appointmentDao = appointmentDao;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public void createAppointment() throws IOException { 
        final String patientCpf;
        final String doctorCrm;
        Date appointmentDate;

        List<Doctor> doctors = doctorService.listDoctors();
        doctorService.listDoctorsView();

        while (true) {
            String tempCrm = ReadValues.readCrm("Digite o CRM do médico: ");

            if (doctors.stream().anyMatch(d -> d.getCrm().equals(tempCrm) && d.getStatus() == Status.ATIVO)) {
                doctorCrm = tempCrm;
                break;
            }

            System.out.println("CRM inválido. Escolha um médico válido e ativo.");
        }

        List<Patient> patients = patientService.listPatients();
        patientService.listPatientsView();

        while (true) {
            String tempCpf = ReadValues.readCpf("Digite o CPF do paciente: ");

            if (patients.stream().anyMatch(p -> p.getCpf().equals(tempCpf) && p.getStatus() == Status.ATIVO)) {
                patientCpf = tempCpf;

                break;
            }

            System.out.println("CPF inválido. Escolha um paciente válido e ativo.");
        }

        AppointmentStatus status = ReadValues.readAppointmentStatus("Digite o status da consulta (A - agendada, C - cancelada, R - realizada): ");

        while (true) {
            appointmentDate = ReadValues.readDate("Digite a data e hora da consulta (DD/MM/YYYY HH:mm): ");

            if (status == AppointmentStatus.CANCELADA) {
                break;
            }

            if (status == AppointmentStatus.REALIZADA) {
                if (appointmentDate.before(new Date())) {
                    break;
                } else {
                    System.out.println("A realização da consulta deve ser uma data passada.");
                }
            }

            if (status == AppointmentStatus.AGENDADA) {
                if (appointmentDate.after(new Date())) {
                    break;
                }

                System.out.println("Data inválida. Para consultas agendadas, escolha uma data futura.");
            }
        }

        String observation = ReadValues.readString("Digite a observação da consulta: ");

        Appointment appointment = new Appointment(doctorCrm, patientCpf, appointmentDate, observation, status, "");

        try {
            String response = appointmentDao.createAppointment(appointment);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao criar consulta: " + e.getMessage());
        }
    }

    public String getAppointment(String id) throws IOException {
        try {   
            return appointmentDao.getAppointment(id);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar consulta: " + e.getMessage());
        }
    }

    public void updateAppointment() throws IOException {
        List<Appointment> appointments = listAppointments();
        int number = 1;

        if (appointments.isEmpty()) {
            Style.printLine(50);
            System.out.println("Não existem consultas cadastradas.");
            Style.printLine(50);
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println("*********** Consulta " + number + " ***********");
            System.out.println(appointment.toString());
            Style.printLine(50);
            number++;
        }

        int option = ReadValues.readMenuOption("Digite o número da consulta que deseja atualizar: ", 1, appointments.size());
        Appointment appointment = appointments.get(option - 1);
        
        String observation = ReadValues.readString("Digite a observação da consulta: ");
        AppointmentStatus status = ReadValues.readAppointmentStatus("Digite o status da consulta (A - agendada, C - cancelada, R - realizada): ");

        appointment.setObservation(observation);
        appointment.setStatus(status);
        try {
            Style.printLine(50);

            System.out.println(appointmentDao.updateAppointment(appointment));

            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public List<Appointment> listAppointments() throws IOException {
        try {
            String json = appointmentDao.listAppointments();

            return JsonParser.parseJson(json, Appointment.class);
        } catch (IOException e) {
            throw new IOException("Erro ao listar consultas: " + e.getMessage());
        }
    }

    public void listAppointmentsView() throws IOException {
        try {
            List<Appointment> appointments = listAppointments();

            if (appointments.isEmpty()) {
                System.out.println("Não existem consultas cadastradas.");

                return;
            }
            
            for (Appointment appointment : appointments) {
                System.out.println(appointment.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar consultas: " + e.getMessage());
        }
    }
}
