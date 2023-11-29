package com.algorimos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class ConversorRomanoDecimalTest {

    @Test
    public void testDecimalParaRomano() {
        // Testes para casos válidos
        assertEquals("I", ConversorRomanoDecimal.decimalParaRomano(1));
        assertEquals("IV", ConversorRomanoDecimal.decimalParaRomano(4));
        assertEquals("IX", ConversorRomanoDecimal.decimalParaRomano(9));
        assertEquals("XLIX", ConversorRomanoDecimal.decimalParaRomano(49));
        assertEquals("MCMLXXXVII", ConversorRomanoDecimal.decimalParaRomano(1987));
        assertEquals("MMMCMXCIX", ConversorRomanoDecimal.decimalParaRomano(3999));

        // Testes para valores fora do intervalo
        assertEquals("Não é possível converter o número decimal informado para algarismos romanos.",
                ConversorRomanoDecimal.decimalParaRomano(0));
        assertEquals("Não é possível converter o número decimal informado para algarismos romanos.",
                ConversorRomanoDecimal.decimalParaRomano(4000));
    }

    @ParameterizedTest
    @CsvSource({
        "I, 1",
        "II, 2",
        "III, 3",
        "IV, 4",
        "V, 5",
        "VI, 6",
        "VII, 7",
        "VIII, 8",
        "IX, 9",
        "X, 10",
        "XX, 20",
        "XXX, 30",
        "XL, 40",
        "L, 50",
        "LX, 60",
        "LXX, 70",
        "LXXX, 80",
        "XC, 90",
        "C, 100",
        "CXL, 140",
        "IV, 4",  // Teste para a regra IV (soma e subtração)
        "IX, 9",  // Teste para a regra IX (soma e subtração)
        "XL, 40", // Teste para a regra XL (soma e subtração)
        "XC, 90"  // Teste para a regra XC (soma e subtração)
    })
    public void testRomanoParaDecimalNumerosSimples(String romano, int expected) {
        assertEquals(expected, ConversorRomanoDecimal.romanoParaDecimal(romano));
    }



    @ParameterizedTest
    @CsvSource({
        "III, 3",     // Teste para a regra III (não permitida)
        "VV, 10",     // Teste para a regra VV (não permitida)
        "IL, 49",     // Teste para a regra IL (não permitida)
        "IC, 99"      // Teste para a regra IC (não permitida)
    })
    public void testRomanoParaDecimalSimbolosInvalidos(String romano, int expected) {
        assertEquals(expected, ConversorRomanoDecimal.romanoParaDecimal(romano));
    }

    @ParameterizedTest
    @CsvSource({
        "IV, 4",
        "IX, 9",
        "XL, 40",
        "XC, 90",
        "CD, 400",
        "CM, 900",
        "MMMMCMXCIXI, 0", // Teste para valor decimal igual a zero
        "4000, 0"         // Teste para valor decimal maior que 3999
    })
    public void testRomanoParaDecimalInvalidos(String romano, int expected) {
        assertEquals(expected, ConversorRomanoDecimal.romanoParaDecimal(romano));
    }
}
