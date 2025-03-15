package com.marina.model;

import com.marina.util.Formatter;

public class Doctor extends Person {
    private String crm;

    public Doctor(String name, String cpf, String phone, String profile, String status, String userAutomation, String crm) {
        super(name, cpf, phone, profile, status, userAutomation);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String getDetails() {
        return "Médico: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone()) + ", CRM: " + Formatter.formatCrm(crm);
    }
}
