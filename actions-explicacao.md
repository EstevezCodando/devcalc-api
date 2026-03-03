# Workflows e Actions no GitHub Actions (DevCalc)

## Diferença entre workflow e action

Um **workflow** é o “orquestrador” do pipeline. Ele é definido em um arquivo YAML dentro de `.github/workflows/` e descreve **quando** o pipeline roda (gatilhos como `push`, `pull_request`, `workflow_dispatch`, etc) e **o que** será executado (jobs, steps, dependências, condições, permissões e variáveis). Em outras palavras, o workflow conecta peças e define a sequência de execução.

Uma **action** é uma “peça” reutilizável que executa uma tarefa específica dentro de um workflow. A action pode ser:

- **Externa** (Marketplace), referenciada por `uses: owner/repo@versao`
- **Interna** (no próprio repositório), referenciada por `uses: ./.github/actions/minha-action`

O workflow compõe um pipeline chamando actions (e/ou comandos `run:`). A action encapsula lógica para ser reaproveitada em vários projetos e workflows.

## Como uma action é estruturada internamente

Internamente, uma action é empacotada como um pequeno artefato de automação. Os formatos mais comuns são:

1. **JavaScript action**  
   Implementada em Node.js, com um arquivo principal (ex.: `dist/index.js`) e dependências empacotadas.

2. **Docker action**  
   Executa dentro de um container Docker definido por um `Dockerfile`.

3. **Composite action**  
   Uma action escrita em YAML que combina vários steps (ela própria chama `run:` e/ou `uses:`). É útil para padronizar rotinas sem precisar publicar código JavaScript.

Importante saber que tem para diversas linguagens, para definir testes, e verificações com precisão.
evitando o famoso " Na minha maquina roda"

Em todos os casos, existe um arquivo de manifesto que descreve a action.

## Papel do arquivo `action.yml`

O arquivo `action.yml` (ou `action.yaml`) é o manifesto da action. Ele define, entre outros pontos:

- **name / description**: metadados da action
- **inputs**: parâmetros de entrada que o workflow pode passar via `with:`
- **outputs**: valores de saída que a action expõe para steps seguintes
- **runs**: como a action é executada (JavaScript, Docker ou Composite)
- **branding** (opcional): ícone/cor para Marketplace

Exemplo conceitual (simplificado) de `action.yml`:

```yaml
name: Minha Action
inputs:
  exemplo_input:
    required: true
outputs:
  exemplo_output:
    description: "Resultado produzido"
runs:
  using: "node20"
  main: "dist/index.js"
```

É esse manifesto que permite ao GitHub Actions validar quais inputs existem e como executar a action.

## Exemplo real no DevCalc: “Checkstyle for Java”

No DevCalc, utilizamos a action do Marketplace **Checkstyle for Java**, chamando-a dentro do workflow (`ci.yml`) por meio de `uses:`.

A chamada tem este formato:

```yaml
- name: Checkstyle
  uses: dbelyaev/action-checkstyle@v3.6.0
  with:
    github_token: ${{ secrets.GITHUB_TOKEN }}
    reporter: github-check
    level: warning
    filter_mode: nofilter
```

### Como o workflow chama a action

- `uses: dbelyaev/action-checkstyle@v3.6.0` indica **qual action** será executada e **qual versão** (tag) será usada.
- O bloco `with:` passa os **inputs** definidos no `action.yml` dessa action.

### Como os parâmetros são passados

- `github_token: ${{ secrets.GITHUB_TOKEN }}` fornece o token automático do GitHub Actions, permitindo que a action publique resultados (checks/anotações) na execução.
- `reporter`, `level` e `filter_mode` configuram como o relatório é apresentado e qual severidade será aplicada.

Em resumo: o **workflow** define o pipeline e chama a **action**; a **action** encapsula a lógica do Checkstyle; e o arquivo **`action.yml`** da action define oficialmente os inputs aceitos, outputs (se houver) e a forma de execução.
