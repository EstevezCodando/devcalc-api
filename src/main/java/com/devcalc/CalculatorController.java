package com.devcalc;

import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;

import java.util.Objects;

public final class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = Objects.requireNonNull(calculatorService, "calculatorService");
    }

    public void registrarRotas(Javalin app) {
        app.get("/add", ctx -> responder(ctx, Operacao.SOMA));
        app.get("/subtract", ctx -> responder(ctx, Operacao.SUBTRACAO));
        app.get("/multiply", ctx -> responder(ctx, Operacao.MULTIPLICACAO));
        app.get("/divide", ctx -> responder(ctx, Operacao.DIVISAO));
    }

    private void responder(Context ctx, Operacao operacao) {
        double a = lerDoubleObrigatorio(ctx, "a");
        double b = lerDoubleObrigatorio(ctx, "b");

        double resultado = switch (operacao) {
            case SOMA -> calculatorService.somar(a, b);
            case SUBTRACAO -> calculatorService.subtrair(a, b);
            case MULTIPLICACAO -> calculatorService.multiplicar(a, b);
            case DIVISAO -> calculatorService.dividir(a, b);
        };

        ctx.result(formatarNumero(resultado));
    }

    private static double lerDoubleObrigatorio(Context ctx, String nomeParametro) {
        String valor = ctx.queryParam(nomeParametro);
        if (valor == null || valor.isBlank()) {
            throw new BadRequestResponse("Parâmetro obrigatório ausente: " + nomeParametro);
        }
        try {
            return Double.parseDouble(valor.trim());
        } catch (NumberFormatException ex) {
            throw new BadRequestResponse("Parâmetro inválido: " + nomeParametro);
        }
    }

    private static String formatarNumero(double valor) {
        if (valor == (long) valor) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }

    private enum Operacao {
        SOMA,
        SUBTRACAO,
        MULTIPLICACAO,
        DIVISAO
    }
}
