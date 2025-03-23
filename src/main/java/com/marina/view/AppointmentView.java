package com.marina.view;

import java.io.IOException;

import com.marina.services.AppointmentService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;


public class AppointmentView {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Consulta",
        "[2] - Listar Consultas",
        "[3] - Atualizar Consulta",
        "[4] - Voltar"
    };
    
    private static final IMenuOption[] METHODS = {
        () -> AppointmentService.createAppointment(),
        () -> AppointmentService.listAppointmentsView(),
        () -> AppointmentService.updateAppointment(),
        () -> showMenu()
    };

    public static void showAppointmentMenu() throws IOException {
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
