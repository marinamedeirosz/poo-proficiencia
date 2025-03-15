package com.marina.model;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.utils.Formatter;

public abstract class Person {
    private String name;
    private String cpf;
    private String phone;
    private Profile profile;           // 'P' - Paciente, 'M' - Médico
    private Status status;             // 'A' - Ativo, 'I' - Inativo
    private YesOrNo userAutomation;     // 'S' - Sim, 'N' - Não

    public Person(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.profile = profile != null ? profile : Profile.PACIENTE;
        this.status = status != null ? status : Status.ATIVO;
        this.userAutomation = userAutomation != null ? userAutomation : YesOrNo.SIM;
    }

    public abstract String getDetails();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public YesOrNo getUserAutomation() {
        return userAutomation;
    }

    public void setUserAutomation(YesOrNo userAutomation) {
        this.userAutomation = userAutomation;
    }

    @Override
    public String toString() {
        return "Pessoa: " + name + ", CPF: " + Formatter.formatCpf(cpf);
    }
}
