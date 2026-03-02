package com.devcalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void deveSomarDoisNumeros() {
        double resultado = calculatorService.somar(10, 5);
        assertEquals(15.0, resultado, 0.0);
    }

    @Test
    void deveSubtrairDoisNumeros() {
        double resultado = calculatorService.subtrair(10, 5);
        assertEquals(5.0, resultado, 0.0);
    }

    @Test
    void deveMultiplicarDoisNumeros() {
        double resultado = calculatorService.multiplicar(10, 5);
        assertEquals(50.0, resultado, 0.0);
    }

    @Test
    void deveDividirDoisNumeros() {
        double resultado = calculatorService.dividir(10, 5);
        assertEquals(2.0, resultado, 0.0);
    }

    @Test
    void deveFalharAoDividirPorZero() {
        assertThrows(IllegalArgumentException.class, () -> calculatorService.dividir(10, 0));
    }
}
