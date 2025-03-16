package com.marina.services;

import java.io.IOException;
import java.util.Date;

import com.marina.dao.ConnectionDao;
import com.marina.enums.Status;
import com.marina.model.Appointment;
import com.marina.utils.Formatter;
import com.marina.utils.ReadValues;

public class AppointmentService {
    private static final String ENDPOINT = "consulta";

    public static void createAppointment() throws IOException { 
        String doctorCrm = ReadValues.readCrm("Digite o CRM do médico: ");
        String patientCpf = ReadValues.readCpf("Digite o CPF do paciente: ");
        Date appointmentDate = ReadValues.readDate("Digite a data da consulta: ");
        String observation = ReadValues.readString("Digite a observação da consulta: ");
        Status status = ReadValues.readStatus("Digite o status da consulta: ");

        // Appointment appointment = new Appointment(doctorCrm, patientCpf, appointmentDate, observation, status);

        String jsonData = "{"
                + "\"doctorCrm\": \"" + doctorCrm + "\","
                + "\"patientCpf\": \"" + patientCpf + "\","
                + "\"appointmentDate\": \"" + Formatter.formatDate(appointmentDate) + "\","
                + "\"observation\": \"" + observation + "\","
                + "\"status\": \"" + status + "\""
                + "}";
        try {
            ConnectionDao.makePostRequest(ENDPOINT, jsonData);
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

    public static void updateAppointment(Appointment appointment, String id) throws IOException {
        String jsonData = "{"
                + "\"observation\": \"" + appointment.getObservation() + "\","
                + "\"status\": \"" + appointment.getStatus() + "\""
                + "}";  
        try {
            ConnectionDao.makePutRequest(ENDPOINT + "/" + id, jsonData);
        } catch (IOException e) {
            throw new IOException("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public static void deleteAppointment(String id) throws IOException {
        try {
            ConnectionDao.makeDeleteRequest(ENDPOINT + "/" + id);
        } catch (IOException e) {
            throw new IOException("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public static String listAppointments() throws IOException {
        try {
            return ConnectionDao.makeGetRequest(ENDPOINT);
        } catch (IOException e) {
            throw new IOException("Erro ao listar consultas: " + e.getMessage());
        }
    }
    
}