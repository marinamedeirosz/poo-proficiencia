package com.marina.dao.interfaces;

import java.io.IOException;

import com.marina.model.Doctor;

public interface DoctorDao {
    String createDoctor(Doctor doc) throws IOException;
    String getDoctor(String cpf) throws IOException;
    void updateDoctor(Doctor doc) throws IOException;
    void deleteDoctor(String cpf) throws IOException;
    String listDoctors() throws IOException;
    String addSpecialty(String crm, String specialty, Integer level) throws IOException;
    String listSpecialties(String crm) throws IOException;
}
