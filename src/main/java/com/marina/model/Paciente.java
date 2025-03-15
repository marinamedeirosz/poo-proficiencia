package com.marina.model;

import java.util.Date;

import com.marina.util.Formatter;

public class Paciente extends Pessoa {

    public Paciente(int id, Date createdDate, String name, String cpf, String phone, String profile, String situation, String userAutomation) {
        super(id, createdDate, name, cpf, phone, profile, situation, userAutomation);
    }

    @Override
    public String getEntityDetails() {
        return "Paciente: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone());
    }
}
