package com.marina.factory;

import com.marina.exception.InvalidProfileException;
import com.marina.model.Doctor;
import com.marina.model.Patient;
import com.marina.model.Person;

public class PessoaFactory {

    public static Person createPessoa(String name, String cpf, String phone, String profile, String situation, String userAutomation, String crm) {
       switch (profile) {
            case "M":
                return new Doctor(name, cpf, phone, profile, situation, userAutomation, crm);
            case "P":
                return new Patient(name, cpf, phone, profile, situation, userAutomation);
            default:
                throw new InvalidProfileException(profile);
        }
    }
}
