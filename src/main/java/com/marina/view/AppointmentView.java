package com.marina.view;

import java.io.IOException;

import com.marina.dao.impl.AppointmentDaoImpl;
import com.marina.dao.impl.DoctorDaoImpl;
import com.marina.dao.impl.PatientDaoImpl;
import com.marina.dao.impl.SpecialtyDaoImpl;
import com.marina.services.AppointmentService;
import com.marina.services.DoctorService;
import com.marina.services.PatientService;
import com.marina.services.SpecialtyService;
import com.marina.utils.ReadValues;
import com.marina.utils.Style;
import com.marina.view.MenuView.IMenuOption;
import static com.marina.view.MenuView.showMenu;


public class AppointmentView {
    private static final String[] OPTIONS = {
        "[1] - Cadastrar Consulta",
        "[2] - Listar Consultas",
        "[3] - Atualizar Consulta",
        "[4] - Voltar"
    };
    
    private static final AppointmentService appointmentService = new AppointmentService(
        new AppointmentDaoImpl(),
        new DoctorService(new DoctorDaoImpl(), new SpecialtyService(new SpecialtyDaoImpl())),
        new PatientService(new PatientDaoImpl())
    );

    private static final IMenuOption[] METHODS = {
        () -> appointmentService.createAppointment(),
        () -> appointmentService.listAppointmentsView(),
        () -> appointmentService.updateAppointment(),
        () -> showMenu()
    };
    

    public static void showAppointmentMenu() throws IOException {
        while (true) {
            System.out.println("Selecione uma opção:");
            for (String option : OPTIONS) {
                System.out.println(option);
            }
            Style.printLine(50);
            int option = ReadValues.readMenuOption("Digite a opção desejada: ", 1, OPTIONS.length);
            METHODS[option - 1].run();
        }
    }    
}
