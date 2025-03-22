package com.marina;

import java.io.IOException;

public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        try {
            Menu.showLoginMenu();
        } catch (IOException e) {
            System.out.println("Erro ao mostrar menu: " + e.getMessage());
        }
    }
}
