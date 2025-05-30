package com.marina.view;

import java.io.IOException;

import com.marina.services.SpecialtyService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;

public class SpecialtyView {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Especialidade",
        "[2] - Listar Especialidades",
        "[3] - Voltar"
    };

    private static final SpecialtyService specialtyService = new SpecialtyService();

    private static final IMenuOption[] METHODS = {
        () -> specialtyService.createSpecialty(),
        () -> specialtyService.listSpecialtiesView(),
        () -> showMenu()
    };

    public static void showSpecialtyMenu() throws IOException {
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
