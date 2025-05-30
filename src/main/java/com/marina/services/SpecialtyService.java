package com.marina.services;

import java.io.IOException;

import com.marina.dao.SpecialtyDao;
import com.marina.model.Specialty;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class SpecialtyService {
    public void createSpecialty() throws IOException {
        String description = ReadValues.readString("Digite a descrição da especialidade: ");

        Specialty specialty = new Specialty();
        specialty.setDescription(description);

        try {
            String response = SpecialtyDao.createSpecialty(specialty);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao criar especialidade: " + e.getMessage());
        }
    }
}
