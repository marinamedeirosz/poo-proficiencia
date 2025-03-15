package com.marina.model;

import java.util.Date;

import com.marina.util.Formatter;

public class Pessoa extends BaseEntity {
    private String name;
    private String cpf;
    private String phone;
    private String profile;            // 'P' - Paciente, 'M' - Médico
    private String situation;          // 'A' - Ativo, 'I' - Inativo
    private String userAutomation;     // 'S' - Sim, 'N' - Não

    public Pessoa(int id, Date createdDate, String name, String cpf, String phone, String profile, String situation, String userAutomation) {
        super(id, createdDate);
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.profile = profile;
        this.situation = situation;
        this.userAutomation = userAutomation;
    }

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getUserAutomation() {
        return userAutomation;
    }

    public void setUserAutomation(String userAutomation) {
        this.userAutomation = userAutomation;
    }

    @Override
    public String getEntityDetails() {
        return "Pessoa: " + name + ", CPF: " + Formatter.formatCpf(cpf);
    }

    @Override
    public String toString() {
        return getEntityDetails();
    }
}
