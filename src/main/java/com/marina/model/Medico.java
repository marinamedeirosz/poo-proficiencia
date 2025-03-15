package com.marina.model;

import java.util.Date;

import com.marina.util.Formatter;

public class Medico extends Pessoa {
    private String crm;

    public Medico(int id, Date createdDate, String name, String cpf, String phone, String profile, String situation, String userAutomation, String crm) {
        super(id, createdDate, name, cpf, phone, profile, situation, userAutomation);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String getEntityDetails() {
        return "Médico: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone()) + ", CRM: " + Formatter.formatCrm(crm);
    }
}
