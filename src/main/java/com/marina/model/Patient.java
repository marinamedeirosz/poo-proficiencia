package com.marina.model;

import com.marina.util.Formatter;

public class Patient extends Person {

    public Patient(String name, String cpf, String phone, String profile, String status, String userAutomation) {
        super(name, cpf, phone, profile, status, userAutomation);
    }

    @Override
    public String getDetails() {
        return "Paciente: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone());
    }
}
