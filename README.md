[![ci](https://github.com/EstevezCodando/devcalc-api/actions/workflows/ci.yml/badge.svg)](https://github.com/EstevezCodando/devcalc-api/actions/workflows/ci.yml)

# DevCalc API

API REST em Java para operações matemáticas básicas, criada como base
para implementação e validação de pipelines de CI/CD com GitHub Actions.

---

Versão: 0.1 test

## Objetivo

A DevCalc fornece operações de soma, subtração, multiplicação e divisão
via HTTP. O projeto é utilizado para demonstrar:

- Build automatizado
- Testes automatizados
- Empacotamento (.jar)
- Simulação de deploy
- Execução manual de workflow

---

## Tecnologias

- Java 25
- Maven (Maven Wrapper)
- Javalin
- JUnit 5
- GitHub Actions

O projeto utiliza Maven Wrapper, não sendo necessário ter Maven
instalado globalmente.

---

## Estrutura do Projeto

```
src/
 ├── main/java/com/devcalc/
 │    ├── App.java
 │    ├── CalculatorController.java
 │    └── CalculatorService.java
 │
 └── test/java/com/devcalc/
      └── CalculatorServiceTest.java
```

---

## Endpoints

Todos os endpoints utilizam método GET e recebem os parâmetros `a` e `b`.

- `/add?a=10&b=5`
- `/subtract?a=10&b=5`
- `/multiply?a=10&b=5`
- `/divide?a=10&b=5`
- `/health`

---

## Execução Local

### Executar testes

Git Bash:

```bash
./mvnw clean test
```

PowerShell:

```powershell
.\mvnw.cmd clean test
```

---

### Gerar artefato (.jar)

```bash
./mvnw clean package
```

Arquivo gerado em:

```
target/devcalc-api-1.0.0.jar
```

---

### Executar aplicação

Via IntelliJ, execute `com.devcalc.App`.

Ou via terminal:

```bash
java -jar target/devcalc-api-1.0.0.jar
```

A aplicação sobe por padrão em:

```
http://localhost:7070
```

Para alterar a porta:

```bash
java -Dport=7071 -jar target/devcalc-api-1.0.0.jar
```

---

## CI/CD

O projeto possui pipeline configurado em `.github/workflows/ci.yml` com:

- checkout
- build
- test
- package
- deploy (simulado)

O workflow é executado automaticamente em:

- push na branch `main`
- pull requests que modificam arquivos em `src/`

Também suporta execução manual via `workflow_dispatch`.
