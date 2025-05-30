package com.marina.model;

import java.util.List;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.utils.Formatter;

public class Doctor extends Person {
    private String crm;
    private List<Specialty> specialties;

    public Doctor(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation, String crm) {
        super(name, cpf, phone, profile, status, userAutomation);
        this.crm = crm;
    }

    public Doctor() {
        super();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public String getDetails() {
        return "Médico: " + getName() + "\n" +
               "CPF: " + Formatter.formatCpf(getCpf()) + "\n" +
               "Telefone: " + Formatter.formatPhone(getPhone()) + "\n" +
               "CRM: " + Formatter.formatCrm(crm) + "\n" +
               "Situação: " + getStatus().getText();
    }
    
    @Override
    public String toString() {
        return getDetails();
    }
}
