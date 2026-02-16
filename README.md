# DevCalc API 

Versão: 0.1
## Objetivo

A DevCalc é uma API REST desenvolvida em Java que disponibiliza
operações matemáticas básicas: soma, subtração, multiplicação e divisão.

O projeto foi criado para servir como base para implementação e
validação de pipelines de CI/CD com GitHub Actions, incluindo build,
testes automatizados e empacotamento.

------------------------------------------------------------------------

## Tecnologias

-   Java 25\
-   Maven (com Maven Wrapper)\
-   Javalin\
-   JUnit 5

O projeto utiliza Maven Wrapper, portanto não é necessário ter o Maven
instalado globalmente.

------------------------------------------------------------------------

## Estrutura

    src/
     ├── main/java/com/devcalc/
     │    ├── App.java
     │    ├── CalculatorController.java
     │    └── CalculatorService.java
     │
     └── test/java/com/devcalc/
          └── CalculatorServiceTest.java

------------------------------------------------------------------------

## Endpoints

Todos os endpoints utilizam método GET e recebem os parâmetros `a` e
`b`.

-   `/add?a=10&b=5` → 15\
-   `/subtract?a=10&b=5` → 5\
-   `/multiply?a=10&b=5` → 50\
-   `/divide?a=10&b=5` → 2\
-   `/health` → ok

------------------------------------------------------------------------

## Executando Localmente

### Executar testes

Git Bash:

``` bash
./mvnw clean test
```

PowerShell:

``` powershell
.\mvnw.cmd clean test
```

------------------------------------------------------------------------

### Gerar o JAR

``` bash
./mvnw package
```

O arquivo será gerado em:

    target/devcalc-api-1.0.0.jar

------------------------------------------------------------------------

### Executar a aplicação

Pelo IntelliJ, execute a classe `com.devcalc.App`.

Ou via terminal:

``` bash
java -jar target/devcalc-api-1.0.0.jar
```

A aplicação sobe por padrão em:

    http://localhost:7070

Para alterar a porta:

``` bash
java -Dport=7071 -jar target/devcalc-api-1.0.0.jar
```
