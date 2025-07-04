package com.marina.view;

import java.io.IOException;

import com.marina.dao.impl.DoctorDaoImpl;
import com.marina.dao.impl.SpecialtyDaoImpl;
import com.marina.services.DoctorService;
import com.marina.services.SpecialtyService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;

public class DoctorView {

    private static final String[] OPTIONS = {
        "[1] - Cadastrar Médico",
        "[2] - Listar Médicos",
        "[3] - Adicionar Especialidade",
        "[4] - Voltar"
    };

    private static final DoctorService doctorService = new DoctorService(new DoctorDaoImpl(), new SpecialtyService(new SpecialtyDaoImpl()));

    private static final IMenuOption[] METHODS = {
        () -> doctorService.createDoctor(),
        () -> doctorService.listDoctorsView(),
        () -> doctorService.addSpecialty(),
        () -> showMenu()
    };

    public static void showDoctorMenu() throws IOException {
        while (true) {
            System.out.println("Selecione uma opção:");
            for (String option : OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, OPTIONS.length);
            METHODS[option - 1].run();
        }
    }
}
