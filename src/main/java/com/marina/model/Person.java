package com.marina.model;

import com.marina.util.Formatter;

public abstract class Person {
    private String name;
    private String cpf;
    private String phone;
    private String profile;            // 'P' - Paciente, 'M' - Médico
    private String status;             // 'A' - Ativo, 'I' - Inativo
    private String userAutomation;     // 'S' - Sim, 'N' - Não

    public Person(String name, String cpf, String phone, String profile, String status, String userAutomation) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.profile = profile != null ? profile : "P";
        this.status = status != null ? status : "A";
        this.userAutomation = userAutomation != null ? userAutomation : "S";
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAutomation() {
        return userAutomation;
    }

    public void setUserAutomation(String userAutomation) {
        this.userAutomation = userAutomation;
    }

    @Override
    public String toString() {
        return "Pessoa: " + name + ", CPF: " + Formatter.formatCpf(cpf);
    }
}
