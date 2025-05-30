package com.marina.services;

import java.io.IOException;
import java.util.List;

import com.marina.dao.SpecialtyDao;
import com.marina.model.Specialty;
import com.marina.utils.JsonParser;
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

    public List<Specialty> listSpecialties() throws IOException {
        try {
            String json;
            json = SpecialtyDao.listSpecialties();
            List<Specialty> Specialties = JsonParser.parseJson(json, Specialty.class);

            return Specialties;
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }

    public void listSpecialtiesView() throws IOException {
        try {
            List<Specialty> Specialties = listSpecialties();

            if (Specialties.isEmpty()) {
                System.out.println("Não existem especialidades cadastrados.");
                return;
            }

            for (Specialty Specialty : Specialties) {
                System.out.println(Specialty.toString());
                Style.printLine(50);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar especialidades: " + e.getMessage());
        }
    }
}
