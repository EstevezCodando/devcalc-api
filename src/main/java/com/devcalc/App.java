package com.devcalc;

import io.javalin.Javalin;

public final class App {

    private static final int PORTA_PADRAO = 7070;

    private App() {
    }

    public static void main(String[] args) {
        int porta = resolverPorta();

        CalculatorService calculatorService = new CalculatorService();
        CalculatorController calculatorController = new CalculatorController(calculatorService);

        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "text/plain; charset=utf-8";
        });

        app.get("/health", ctx -> ctx.result("ok"));
        calculatorController.registrarRotas(app);

        app.start(porta);
        System.out.println("DevCalc rodando em http://localhost:" + porta + "/health");
    }

    private static int resolverPorta() {
        String portaPorSistema = System.getProperty("port");
        if (portaPorSistema != null && !portaPorSistema.isBlank()) {
            return validarPorta(portaPorSistema);
        }

        String portaPorAmbiente = System.getenv("PORT");
        if (portaPorAmbiente != null && !portaPorAmbiente.isBlank()) {
            return validarPorta(portaPorAmbiente);
        }

        return PORTA_PADRAO;
    }

    private static int validarPorta(String valor) {
        try {
            int porta = Integer.parseInt(valor.trim());
            if (porta < 1 || porta > 65535) {
                throw new IllegalArgumentException("Porta fora do intervalo 1..65535: " + porta);
            }
            return porta;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Porta inválida: " + valor, ex);
        }
    }
}
