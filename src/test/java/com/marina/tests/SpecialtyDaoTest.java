package com.marina.tests;

import com.marina.dao.impl.SpecialtyDaoImpl;
import com.marina.model.Specialty;
import com.marina.utils.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class SpecialtyDaoTest {

    private SpecialtyDaoImpl dao;

    @BeforeEach
    void setUp() {
        dao = new SpecialtyDaoImpl();
    }

    @Test
    void shouldListSpecialties() {
        String json = Assertions.assertDoesNotThrow(() -> dao.listSpecialties());

        Assertions.assertNotNull(json);

        List<Specialty> specialties = JsonParser.parseJson(json, Specialty.class);

        Assertions.assertNotEquals(0, specialties.size());
    }

    @Test
    void shouldCreateSpecialty() {
        final String specialty = UUID.randomUUID().toString();

        Assertions.assertDoesNotThrow(() -> dao.createSpecialty(new Specialty(specialty, "")));
    }

}
