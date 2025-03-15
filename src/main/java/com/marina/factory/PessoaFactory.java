package com.marina.factory;

import com.marina.exception.InvalidProfileException;
import com.marina.model.Medico;
import com.marina.model.Paciente;
import com.marina.model.Pessoa;

public class PessoaFactory {

    public static Pessoa createPessoa(String name, String cpf, String phone, String profile, String situation, String userAutomation, String crm) {
       switch (profile) {
            case "M":
                return new Medico(name, cpf, phone, profile, situation, userAutomation, crm);
            case "P":
                return new Paciente(name, cpf, phone, profile, situation, userAutomation);
            default:
                throw new InvalidProfileException(profile);
        }
    }
}
