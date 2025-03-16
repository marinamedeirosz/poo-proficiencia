package com.marina;

import java.io.IOException;

import com.marina.services.PatientService;

public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        /* try {
            Menu.showMenu();
        } catch (IOException e) {
            System.out.println("Erro ao mostrar menu: " + e.getMessage());
        } */
       PatientService.listPatients();
    }
}
