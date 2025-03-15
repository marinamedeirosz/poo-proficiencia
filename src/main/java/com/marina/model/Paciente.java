package com.marina.model;

import com.marina.util.Formatter;

public class Paciente extends Pessoa {

    public Paciente(String name, String cpf, String phone, String profile, String situation, String userAutomation) {
        super(name, cpf, phone, profile, situation, userAutomation);
    }

    @Override
    public String toString() {
        return "Paciente: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone());
    }
}
