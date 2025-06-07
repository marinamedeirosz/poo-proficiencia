package com.marina.factory;

import com.marina.enums.Profile;
import com.marina.enums.Status;
import com.marina.enums.YesOrNo;
import com.marina.exception.InvalidProfileException;
import com.marina.model.Doctor;
import com.marina.model.Patient;
import com.marina.model.Person;

public class PersonFactory {

    public static Person createPerson(String name, String cpf, String phone, Profile profile, Status status, YesOrNo userAutomation, String crm) {
       switch (profile) {
            case MEDICO -> {
                return new Doctor(name, cpf, phone, profile, status, userAutomation, crm);
            }
            case PACIENTE -> {
                return new Patient(name, cpf, phone, profile, status, userAutomation);
            }
            default -> throw new InvalidProfileException(profile.toString());
        }
    }
}
