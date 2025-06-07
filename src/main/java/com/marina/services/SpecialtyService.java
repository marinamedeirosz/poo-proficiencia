package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.interfaces.SpecialtyDao;
import com.marina.model.Specialty;
import com.marina.utils.JsonParser;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class SpecialtyService {

    private final SpecialtyDao specialtyDao;

    // Constructor injection for SpecialtyDao
    public SpecialtyService(SpecialtyDao specialtyDao) {
        this.specialtyDao = specialtyDao;
    }

    public void createSpecialty() throws IOException {
        String description = ReadValues.readString("Digite a descrição da especialidade: ");

        Specialty specialty = new Specialty();
        specialty.setDescription(description);

        try {
            String response = specialtyDao.createSpecialty(specialty);
            Style.printLine(50);
            System.out.println(response);
            Style.printLine(50);
        } catch (IOException e) {
            throw new IOException("Erro ao criar especialidade: " + e.getMessage());
        }
    }

    public List<Specialty> listSpecialties() throws IOException {
        try {
            String json = specialtyDao.listSpecialties();
            List<Specialty> specialties = JsonParser.parseJson(json, Specialty.class);

            return specialties;
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }

    public void listSpecialtiesView() throws IOException {
        try {
            List<Specialty> specialties = listSpecialties();

            if (specialties.isEmpty()) {
                System.out.println("Não existem especialidades cadastradas.");
                return;
            }

            for (Specialty specialty : specialties) {
                System.out.println(specialty.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }
}
