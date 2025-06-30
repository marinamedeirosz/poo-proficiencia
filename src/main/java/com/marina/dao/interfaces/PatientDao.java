package com.marina.dao.interfaces;

import java.io.IOException;

import com.marina.model.Patient;

public interface PatientDao {
    String createPatient(Patient patient) throws IOException;
    void updatePatient(Patient patient) throws IOException;
    void deletePatient(String cpf) throws IOException;
    String listPatients() throws IOException;
}
