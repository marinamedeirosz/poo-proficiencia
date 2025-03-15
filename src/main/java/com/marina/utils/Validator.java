package com.marina.utils;

import com.marina.exception.InvalidCpfException;

public class Validator {
    public static boolean isValid(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new InvalidCpfException("deve ter 11 dígitos.");
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            throw new InvalidCpfException("não pode ter todos os dígitos iguais.");
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit == 10 || firstDigit == 11) {
            firstDigit = 0;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit == 10 || secondDigit == 11) {
            secondDigit = 0;
        }

        return cpf.charAt(9) == (char) ('0' + firstDigit) && cpf.charAt(10) == (char) ('0' + secondDigit);
    }

    public static boolean isValidPhone(String phone) {
        phone = phone.replaceAll("[^0-9]", "");
        return phone.length() == 11;
    }
}
