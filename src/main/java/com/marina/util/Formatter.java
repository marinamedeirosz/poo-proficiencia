package com.marina.util;

public class Formatter {
    public static String formatCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return "";
        }
        if (cpf.length() != 11) {
            return cpf;
        }
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static String unformatCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return "";
        }
        if (cpf.length() != 14) {
            return cpf;
        }
        return cpf.replaceAll("\\.", "").replaceAll("-", "");
    }

    public static String formatPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return "";
        }
        return phone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }
    
    public static String unformatPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return "";
        }
        return phone.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", "");
    }

    public static String formatCrm(String crm) {
        if (crm == null || crm.isEmpty()) {
            return "";
        }
        return crm.replaceAll("(\\d{2})(\\d{4})(\\d{1})", "$1/$2-$3");
    }

    public static String unformatCrm(String crm) {
        if (crm == null || crm.isEmpty()) {
            return "";
        }
        return crm.replaceAll("\\/", "").replaceAll("-", "");
    }
}
