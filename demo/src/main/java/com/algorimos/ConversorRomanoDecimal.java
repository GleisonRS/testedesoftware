package com.algorimos;

import java.util.HashMap;
import java.util.Map;

public class ConversorRomanoDecimal {
    private static final Map<Character, Integer> ROMAN_TO_DECIMAL = new HashMap<>();
    
    static {
        ROMAN_TO_DECIMAL.put('I', 1);
        ROMAN_TO_DECIMAL.put('V', 5);
        ROMAN_TO_DECIMAL.put('X', 10);
        ROMAN_TO_DECIMAL.put('L', 50);
        ROMAN_TO_DECIMAL.put('C', 100);
        ROMAN_TO_DECIMAL.put('D', 500);
        ROMAN_TO_DECIMAL.put('M', 1000);
    }

    public static String decimalParaRomano(int decimal) {
        if (decimal <= 0 || decimal > 3999) {
            return "Não é possível converter o número decimal informado para algarismos romanos.";
        }

        StringBuilder romano = new StringBuilder();
        int[] valoresDecimais = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] algarismosRomanos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < valoresDecimais.length; i++) {
            while (decimal >= valoresDecimais[i]) {
                decimal -= valoresDecimais[i];
                romano.append(algarismosRomanos[i]);
            }
        }

        return romano.toString();
    }

    public static int romanoParaDecimal(String romano) {
        if (romano == null || romano.isEmpty()) {
            return 0;
        }

        int decimal = 0;
        int previousValue = 0;
        int repeatedCount = 1; // Contador para verificar repetições

        for (int i = romano.length() - 1; i >= 0; i--) {
            char currentChar = romano.charAt(i);
            int currentValue = ROMAN_TO_DECIMAL.getOrDefault(currentChar, 0);

            if (currentValue == 0) {
                return 0; // Valor romano inválido
            }

            if (currentValue < previousValue) {
                decimal -= currentValue;
                repeatedCount = 1; // Reinicia o contador ao encontrar um valor menor
            } else {
                decimal += currentValue;
                if (currentValue == previousValue) {
                    repeatedCount++;
                    if (repeatedCount > 3) {
                        return 0; // Mais de três repetições
                    }
                } else {
                    repeatedCount = 1; // Reinicia o contador ao encontrar um valor diferente
                }
            }

            previousValue = currentValue;
        }

        return decimal;
    }
}

