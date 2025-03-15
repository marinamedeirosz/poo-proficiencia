package com.marina.model;

import java.util.Date;

import com.marina.enums.AppointmentStatus;
import com.marina.utils.Formatter;

public class Appointment {
    private final Doctor doctor;
    private final Patient patient;
    private final Date date;
    private final String observation;
    private final AppointmentStatus status;

    public Appointment(Doctor doctor, Patient patient, Date date, String observation, AppointmentStatus status) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.observation = observation;
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public String getObservation() {
        return observation;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return AppointmentStatus.AGENDADA.equals(this.status);
    }

    @Override
    public String toString() {
        return "Consulta com " + doctor.getName() + " do paciente " + patient.getName() + " no dia " + Formatter.formatDate(date) + " - Situação: " + status.toString();
    }
}
