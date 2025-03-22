package com.marina.view;

import java.io.IOException;

import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class MenuView {
    private static final String[] OPTIONS = {
        "[1] - Pacientes",
        "[2] - Médicos",
        "[3] - Consultas",
        "[4] - Sair"
    };

    private static final IMenuOption[] METHODS = {
        () -> PatientView.showPatientMenu(),
        () -> DoctorView.showDoctorMenu(),
        () -> AppointmentView.showAppointmentMenu(),
        () -> System.exit(0)
    };

    public static void showMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            Style.printHeart();
            Style.printLine(50);
            System.out.println("Bem-vindo ao sistema de gerenciamento de consultas médicas HEALTH!");
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            for (String option : OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 4);
            METHODS[option - 1].run();
        }
    }

    public interface IMenuOption {
        void run() throws IOException;
    }
}
