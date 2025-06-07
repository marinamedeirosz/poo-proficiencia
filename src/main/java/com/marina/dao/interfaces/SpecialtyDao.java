package com.marina.dao.interfaces;

import java.io.IOException;

import com.marina.model.Specialty;

public interface SpecialtyDao {
    String createSpecialty(Specialty specialty) throws IOException;
    String listSpecialties() throws IOException;
}
