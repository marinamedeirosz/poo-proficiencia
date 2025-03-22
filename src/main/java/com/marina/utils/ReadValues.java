package com.marina.utils;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

import com.marina.enums.AppointmentStatus;
import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;

public class ReadValues {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                Style.printLine(50);
                System.out.println("Por favor, digite um número válido.");
                Style.printLine(50);
            }
        }
    }

    public static Date readDate(String message) {
        // formato: dd/MM/yyyy HH:mm
        while (true) {
            try {
                System.out.print(message);
                String dateString = scanner.nextLine().trim();
                return Formatter.parseDate(dateString);
            } catch (DateTimeParseException e) {
                Style.printLine(50);
                System.out.println("Data inválida. Tente novamente. Formato: dd/MM/yyyy HH:mm");
                Style.printLine(50);
            }
        }
    }

    public static String readCpf(String message) {
        while (true) {
            String cpf = readString(message);
            if (cpf.matches("\\d{11}")) {
                return cpf;
            }
            Style.printLine(50);
            System.out.println("CPF inválido. Tente novamente. Formato: 000.000.000-00");
            Style.printLine(50);
        }
    }
    
    public static String readCrm(String message) {
        while (true) {
            String crm = readString(message);
            if (crm.matches("\\d{6}")) {
                return crm;
            }
            Style.printLine(50);
            System.out.println("CRM inválido. Tente novamente. Formato: 000000");
            Style.printLine(50);
        }
    }   

    public static String readPhone(String message) {
        while (true) {
            String phone = readString(message);
            if (phone.matches("\\d{11}")) {
                return phone;
            }
            Style.printLine(50);
            System.out.println("Telefone inválido. Tente novamente. Formato: 00000000000");
            Style.printLine(50);
        }
    }

    public static Status readStatus(String message) {
        while (true) {
            String status = readString(message);
            if (status.equals("A") || status.equals("a")) {
                return Status.ATIVO;
            }
            if (status.equals("I") || status.equals("i")) {
                return Status.INATIVO;
            }
            Style.printLine(50);
            System.out.println("Status inválido. Tente novamente. Formato: A para ativo ou I para inativo");
            Style.printLine(50);
        }   
    }

    public static YesOrNo readYesNo(String message) {
        while (true) {
            String yesNo = readString(message);
            if (yesNo.equals("S") || yesNo.equals("s")) {
                return YesOrNo.SIM;
            }
            if (yesNo.equals("N") || yesNo.equals("n")) {
                return YesOrNo.NAO;
            }
            Style.printLine(50);
            System.out.println("Opção inválida. Tente novamente. Formato: S para sim ou N para não");
            Style.printLine(50);
        }
    }

    public static Profile readProfile(String message) {
        while (true) {
            String profile = readString(message);
            if (profile.equals("P") || profile.equals("p")) {
                return Profile.PACIENTE;
            }
            if (profile.equals("M") || profile.equals("m")) {
                return Profile.MEDICO;
            }
            Style.printLine(50);
            System.out.println("Perfil inválido. Tente novamente. Formato: P para paciente ou M para médico");
            Style.printLine(50);
        }
    }  

    public static AppointmentStatus readAppointmentStatus(String message) {
        while (true) {
            String appointmentStatus = readString(message);
            if (appointmentStatus.equals("A") || appointmentStatus.equals("a")) {
                return AppointmentStatus.AGENDADA;
            }   
            if (appointmentStatus.equals("C") || appointmentStatus.equals("c")) {
                return AppointmentStatus.CANCELADA;
            }   
            if (appointmentStatus.equals("R") || appointmentStatus.equals("r")) {
                return AppointmentStatus.REALIZADA;
            }
            Style.printLine(50);
            System.out.println("Status da consulta inválido. Tente novamente. Formato: A para agendada ou C para cancelada ou R para realizada");
            Style.printLine(50);
        }
    }

    public static String readName(String message) {
        while (true) {
            String name = readString(message).trim();
            if (name.matches("[\\p{L}\\s]+") && name.split("\\s+").length >= 2) {
                return name;
            }
            Style.printLine(50);
            System.out.println("Nome inválido. Tente novamente. Formato: Nome completo (mínimo dois nomes).");
            Style.printLine(50);
        }
    }

    public static int readMenuOption(String message, int min, int max) {
        while (true) {
            int option = readInt(message);
            if (option >= min && option <= max) {
                Style.printLine(50);
                return option;
            }
            Style.printLine(50);
            System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ".");
            Style.printLine(50);
        }
    }   
}