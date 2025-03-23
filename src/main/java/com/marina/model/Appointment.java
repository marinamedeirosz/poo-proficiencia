package com.marina.model;

import java.util.Date;

import com.marina.enums.AppointmentStatus;
import com.marina.utils.Formatter;

public class Appointment {
    private final String doctor;
    private final String patient;
    private final Date date;
    private String observation;
    private AppointmentStatus status;
    private final String id;

    public Appointment(String doctor, String patient, Date date, String observation, AppointmentStatus status, String id) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.observation = observation;
        this.status = status;
        this.id = id;
    }

    public Appointment() {
        this.doctor = "";
        this.patient = "";
        this.date = new Date();
        this.observation = "";
        this.status = AppointmentStatus.AGENDADA;
        this.id = "";
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return AppointmentStatus.AGENDADA.equals(this.status);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "| Médico: " + doctor + 
             "\n| Paciente: " + patient +
             "\n| Data: " + Formatter.formatDate(date) +
             "\n| Situação: " + status.getText() +
             "\n| Observação: " + (observation == null || observation.isEmpty() ? "Não há observação" : observation);
    }
}
