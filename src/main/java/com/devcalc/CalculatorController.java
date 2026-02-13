package com.devcalc;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//Teste de disparos ci, agora no src
@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public ResponseEntity<String> add(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        double resultado = this.calculatorService.add(a, b);
        return ResponseEntity.ok(formatarResultado(resultado));
    }

    @GetMapping("/subtract")
    public ResponseEntity<String> subtract(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        double resultado = this.calculatorService.subtract(a, b);
        return ResponseEntity.ok(formatarResultado(resultado));
    }

    @GetMapping("/multiply")
    public ResponseEntity<String> multiply(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        double resultado = this.calculatorService.multiply(a, b);
        return ResponseEntity.ok(formatarResultado(resultado));
    }

    @GetMapping("/divide")
    public ResponseEntity<String> divide(
            @RequestParam("a") double a,
            @RequestParam("b") double b
    ) {
        try {
            double resultado = this.calculatorService.divide(a, b);
            return ResponseEntity.ok(formatarResultado(resultado));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    private String formatarResultado(double resultado) {
        return BigDecimal.valueOf(resultado).stripTrailingZeros().toPlainString();
    }
}
