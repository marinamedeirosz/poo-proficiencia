package com.marina;

import java.io.IOException;

import com.marina.services.DoctorService;
import com.marina.services.PatientService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;

public class Menu {
    private static final String[] LOGIN_OPTIONS = {
        "[1] - Login",
        "[2] - Cadastrar",
        "[3] - Sair"
    };

    private static final String[] PATIENT_OPTIONS = {
        "[1] - Cadastrar Paciente",
        "[2] - Listar Pacientes",
        "[3] - Atualizar Paciente",
        "[4] - Deletar Paciente",
        "[5] - Voltar"
    };

    private static final String[] DOCTOR_OPTIONS = {
        "[1] - Cadastrar Médico",
        "[2] - Listar Médicos",
        "[3] - Atualizar Médico",
        "[4] - Deletar Médico",
        "[5] - Voltar"
    };

    private static final String[] CONSULT_OPTIONS = {
        "[1] - Cadastrar Consulta",
        "[2] - Listar Consultas",
        "[3] - Atualizar Consulta",
        "[4] - Deletar Consulta",
        "[5] - Voltar"
    };

    private static final String[] OPTIONS = {
        "[1] - Pacientes",
        "[2] - Médicos",
        "[3] - Consultas",
        "[4] - Sair"
    };

    public static void showMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            for (String option : OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 4);    
            handleMenu(option);
        }
    }

    public static void showLoginMenu() {
        Style.printHeart();
        Style.printLine(50);
        System.out.println("Selecione uma opção:");
        for (String OPTIONS1 : LOGIN_OPTIONS) {
            System.out.println(OPTIONS1);
        }
        Style.printLine(50);
        int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 3);
    }

    public static void showPatientMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            for (String option : PATIENT_OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);    
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 5);
            if (option == 5) {
                return;
            }
            handlePatientMenu(option);
            System.out.println("\nPressione ENTER para continuar...");
            ReadValues.readString("");
        }
    }

    public static void showDoctorMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            for (String option : DOCTOR_OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 5);
            if (option == 5) {
                return;
            }
            handleDoctorMenu(option);
            System.out.println("\nPressione ENTER para continuar...");
            ReadValues.readString("");
        }
    }

    public static void showConsultMenu() throws IOException {
        while (true) {
            Style.printLine(50);
            System.out.println("Selecione uma opção:");
            for (String option : CONSULT_OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, 5);
            if (option == 5) {
                return;
            }
            //handleConsultMenu(option);
            System.out.println("\nPressione ENTER para continuar...");
            ReadValues.readString("");
        }
    }

    public static void handleMenu(int option) throws IOException {
        switch (option) {
            case 1:
                showPatientMenu();
                break;
            case 2:
                showDoctorMenu();
                break;
            case 3:
                showConsultMenu();
                break;
            case 4:
                System.out.println("Saindo do sistema...");
                System.exit(0);
                break;
        }
    }

    public static void handlePatientMenu(int option) throws IOException {
        switch (option) {
            case 1:
                PatientService.createPatient();
                break;
            case 2:
                PatientService.listPatients();
                break;
            case 3:
                PatientService.updatePatient();
                break;
            case 4:
                PatientService.deletePatient();
                break;
            case 5:
                showMenu();
                break;
        }
    }

    public static void handleDoctorMenu(int option) throws IOException {
        switch (option) {
            case 1:
                DoctorService.createDoctor();
                break;
            case 2:
                DoctorService.listDoctors();
                break;
            case 3:
                DoctorService.updateDoctor();
                break;
            case 4:
                DoctorService.deleteDoctor();
                break;
            case 5:
                showMenu();
                break;
        }
    }     
}
