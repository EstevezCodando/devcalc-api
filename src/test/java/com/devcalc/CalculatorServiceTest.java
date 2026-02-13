package com.devcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void add_deveSomar() {
        double resultado = this.calculatorService.add(10, 5);
        assertEquals(15.0d, resultado, 0.000001d);
    }

    @Test
    void subtract_deveSubtrair() {
        double resultado = this.calculatorService.subtract(10, 5);
        assertEquals(5.0d, resultado, 0.000001d);
    }

    @Test
    void multiply_deveMultiplicar() {
        double resultado = this.calculatorService.multiply(10, 5);
        assertEquals(50.0d, resultado, 0.000001d);
    }

    @Test
    void divide_deveDividir() {
        double resultado = this.calculatorService.divide(10, 5);
        assertEquals(2.0d, resultado, 0.000001d);
    }

    @Test
    void divide_comBZero_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> this.calculatorService.divide(10, 0));
    }
}
