package com.marina;

import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class Menu {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Paciente",
        "[2] - Cadastrar Médico",
        "[3] - Cadastrar Consulta",
        "[4] - Listar Pacientes",
        "[5] - Listar Médicos",
        "[6] - Sair"
    };

    public static void showMenu() {
        Style.printHeart();
        Style.printLine(50);
        System.out.println("Selecione uma opção:");
        for (String OPTIONS1 : OPTIONS) {
            System.out.println(OPTIONS1);
        }
        Style.printLine(50);
        System.out.println("Digite a opção desejada: ");
        int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 6);    
    }
}
