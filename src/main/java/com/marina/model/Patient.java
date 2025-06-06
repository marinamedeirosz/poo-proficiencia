package com.marina.model;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.utils.Formatter;

public class Patient extends Person {

    public Patient(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation) {
        super(name, cpf, phone, profile, status, userAutomation);
    }

    public Patient() {
        super();
    }

    @Override
    public String getDetails() {
        return "Paciente: " + getName() + ", CPF: " + Formatter.formatCpf(getCpf()) + ", Telefone: " + Formatter.formatPhone(getPhone()) + ", Situação: " + getStatus().getText();
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
