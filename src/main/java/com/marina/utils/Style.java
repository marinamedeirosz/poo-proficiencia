package com.marina.utils;

import java.util.List;

public class Style {
    public static void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    public static String formatBox(String title, List<?> elements, boolean numbered) {
        StringBuilder block = new StringBuilder();
    
        List<String> lines = 
            elements.stream()
                    .map(Object::toString)
                    .map(String::trim)
                    .toList();
    
        int numberDigits = String.valueOf(lines.size()).length();
    
        List<String> formattedLines = 
            numbered 
            ? lines.stream()
                   .map((line) -> String.format("[ %"+numberDigits+"d ] %s", lines.indexOf(line) + 1, line))
                   .toList()
            : lines;
    
        int maxContentLength = formattedLines.stream()
                                             .mapToInt(String::length)
                                             .max()
                                             .orElse(0);
    
        int totalLength = Math.max(maxContentLength, title.length()) + 2;
    
        int dashCount = (totalLength - title.length()) / 2;
        String header = "|" + "-".repeat(dashCount) + " " + title + " " + "-".repeat(totalLength - title.length() - dashCount) + "|";
    
        String footer = "|" + "-".repeat(totalLength + 2) + "|";
    
        block.append(header).append("\n");
        for (String line : formattedLines) {
            block.append(String.format("| %-"+totalLength+"s |\n", line));
        }
        block.append(footer);
    
        return block.toString();
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
