package com.marina.model;

import com.marina.util.Formatter;

public class Medico extends Pessoa {
    private String crm;

    public Medico(String name, String cpf, String phone, String profile, String situation, String userAutomation, String crm) {
        super(name, cpf, phone, profile, situation, userAutomation);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return "Médico: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone()) + ", CRM: " + Formatter.formatCrm(crm);
    }
}
