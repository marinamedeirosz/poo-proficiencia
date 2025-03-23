package com.marina.view;

import java.io.IOException;

import com.marina.services.DoctorService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;


public class DoctorView {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Médico",
        "[2] - Listar Médicos",
        "[3] - Voltar"
    };

    private static final IMenuOption[] METHODS = {
        () -> DoctorService.createDoctor(),
        () -> DoctorService.listDoctorsView(),
        () -> showMenu()
    };
    
    public static void showDoctorMenu() throws IOException {
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
