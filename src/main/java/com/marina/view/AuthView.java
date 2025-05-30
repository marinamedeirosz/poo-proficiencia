package com.marina.view;

import java.io.IOException;

import com.marina.services.AuthService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class AuthView {
    private static final String[] OPTIONS = {
        "[1] - Login",
        "[2] - Cadastrar",
        "[3] - Sair"
    };

    private static final AuthService authService = new AuthService();

    private static final IMenuOption[] METHODS = {
        () -> authService.login(),
        () -> authService.register(),
        () -> {
            System.exit(0);
        return null;
    }
    }; 

    public static void showLoginMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            
            for (String option : OPTIONS) {
                System.out.println(option);
            }

            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 3);
            String response = METHODS[option - 1].run();
            if (response.equals("Autenticado com sucesso.")) {
                MenuView.showMenu();
            } else {
                Style.printLine(50);
                System.out.println(response);
            }

        }
    }

    public interface IMenuOption {
        String run() throws IOException;
    }
}   
