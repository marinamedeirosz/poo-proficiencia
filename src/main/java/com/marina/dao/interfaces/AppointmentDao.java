package com.marina.dao.interfaces;

import java.io.IOException;

import com.marina.model.Appointment;

public interface AppointmentDao {
    String createAppointment(Appointment appointment) throws IOException;
    String getAppointment(String id) throws IOException;
    String updateAppointment(Appointment appointment) throws IOException;
    void deleteAppointment(String id) throws IOException;
    String listAppointments() throws IOException;
}
