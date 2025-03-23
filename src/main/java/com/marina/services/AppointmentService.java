package com.marina.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.marina.dao.AppointmentDao;
import com.marina.dao.ConnectionDao;
import com.marina.enums.AppointmentStatus;
import com.marina.model.Appointment;
import com.marina.utils.Formatter;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class AppointmentService {
    private static final String ENDPOINT = "consulta";

    public static void createAppointment() throws IOException { 
        String doctorCrm = ReadValues.readCrm("Digite o CRM do médico: ");
        String patientCpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        Date appointmentDate = ReadValues.readDate("Digite a data da consulta: ");
        String observation = ReadValues.readString("Digite a observação da consulta: ");
        AppointmentStatus status = ReadValues.readAppointmentStatus("Digite o status da consulta (A - agendada, C - cancelada, R - realizada): ");

        String jsonData = "{"
                + "\"doctorCrm\": \"" + doctorCrm + "\","   
                + "\"patientCpf\": \"" + patientCpf + "\","
                + "\"appointmentDate\": \"" + Formatter.formatDate(appointmentDate) + "\","
                + "\"observation\": \"" + observation + "\","
                + "\"status\": \"" + status.toString() + "\""
                + "}";
        try {
            String response = ConnectionDao.makePostRequest(ENDPOINT, jsonData);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao criar consulta: " + e.getMessage());
        }
    }

    public static String getAppointment(String id) throws IOException {
        try {   
            return ConnectionDao.makeGetRequest(ENDPOINT + "/" + id);
        } catch (IOException e) {
            throw new IOException("Erro ao buscar consulta: " + e.getMessage());
        }
    }

    public static void updateAppointment() throws IOException {
        List<Appointment> appointments = listAppointments();
        int number = 1;

        for (Appointment appointment : appointments) {
            Style.printLine(50);
            System.out.println(number + " - " + appointment.toString());
            Style.printLine(50);
            number++;
        }

        int option = ReadValues.readInt("Digite o número da consulta que deseja atualizar: ");
        Appointment appointment = appointments.get(option - 1);
        
        String observation = ReadValues.readString("Digite a observação da consulta: ");
        AppointmentStatus status = ReadValues.readAppointmentStatus("Digite o status da consulta (A - agendada, C - cancelada, R - realizada): ");

        appointment.setObservation(observation);
        appointment.setStatus(status);
        try {
            AppointmentDao.updateAppointment(appointment);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public static List<Appointment> listAppointments() throws IOException {
        try {
            String json = ConnectionDao.makeGetRequest(ENDPOINT);
            List<Appointment> appointments = JsonParser.parseJson(json, Appointment.class);

            return appointments;
        } catch (IOException e) {
            throw new IOException("Erro ao listar consultas: " + e.getMessage());
        }
    }

    public static void listAppointmentsView() throws IOException {
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