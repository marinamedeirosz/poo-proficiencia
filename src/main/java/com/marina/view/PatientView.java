package com.marina.view;

import java.io.IOException;

import com.marina.services.PatientService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;

public class PatientView {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Paciente",
        "[2] - Listar Pacientes",
        "[3] - Voltar"
    };

    private static final PatientService patientService = new PatientService();

    private static final IMenuOption[] METHODS = {
        () -> patientService.createPatient(),
        () -> patientService.listPatientsView(),
        () -> showMenu()
    };

    public static void showPatientMenu() throws IOException {
        while (true) {
            System.out.println("Selecione uma opção:");
            for (String option : OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 4);
            METHODS[option - 1].run();
        }
    }
}
