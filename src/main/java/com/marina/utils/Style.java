package com.marina.utils;

public class Style {
    public static void printLine(int length) {
        System.out.println("-".repeat(length));
    }
    
    public static void printHeart() {
        String[] linhas = {
            "",
            "      *****     *****",
            "    **     ** **     **",
            "   **       ***       **",
            "   **                 **",
            "    **     HEALTH    **",
            "      **           **",
            "        **       **",
            "          **   **",
            "            **",
            ""
        };
        
        for (String linha : linhas) {
            System.out.println(linha);
        }
    }
}
