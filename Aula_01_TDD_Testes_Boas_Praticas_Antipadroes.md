# AULA 01 — Estratégias de Testes, TDD, Boas Práticas e Antipadrões

**SERVIÇO NACIONAL DE APRENDIZAGEM INDUSTRIAL – SENAI/SC**

**Curso:** Técnico em Desenvolvimento de Sistemas  
**Unidade Curricular:** Testes de Sistemas  
**Professora:** Karize Viecelli — [@karizeviecelli](https://github.com/karizeviecelli)  
**Data:** 04/08/2026  

---

## Sumário

1. [Plano de Aula — MSEP](#1-plano-de-aula--msep)
2. [Objetivos de Aprendizagem](#2-objetivos-de-aprendizagem)
3. [O que é Teste de Software?](#3-o-que-é-teste-de-software)
4. [Tipos de Testes](#4-tipos-de-testes)
5. [O que é TDD?](#5-o-que-é-tdd)
6. [Ciclo Red, Green e Refactor](#6-ciclo-red-green-e-refactor)
7. [Regra de Ouro do TDD](#7-regra-de-ouro-do-tdd)
8. [Estrutura de um Teste com JUnit 5](#8-estrutura-de-um-teste-com-junit-5)
9. [Anotações mais Utilizadas no JUnit 5](#9-anotações-mais-utilizadas-no-junit-5)
10. [Principais Asserções](#10-principais-asserções)
11. [Padrão AAA](#11-padrão-aaa)
12. [Padrões de Desenvolvimento para Código Testável](#12-padrões-de-desenvolvimento-para-código-testável)
13. [Boas Práticas em TDD e Testes](#13-boas-práticas-em-tdd-e-testes)
14. [Antipadrões](#14-antipadrões)
15. [Exemplo Completo — Calculadora](#15-exemplo-completo--calculadora)
16. [Exemplo Completo — Multa de Biblioteca](#16-exemplo-completo--multa-de-biblioteca)
17. [Testes Parametrizados](#17-testes-parametrizados)
18. [Testes de Exceção](#18-testes-de-exceção)
19. [Testes com Objetos e Listas](#19-testes-com-objetos-e-listas)
20. [Atividades Práticas](#20-atividades-práticas)
21. [Desafio de Acessibilidade](#21-desafio-de-acessibilidade)
22. [Critérios de Avaliação](#22-critérios-de-avaliação)
23. [Checklist da Aula](#23-checklist-da-aula)
24. [Referências](#24-referências)

---

# 1. Plano de Aula — MSEP

| Aula/Data | Capacidade | Conhecimentos Relacionados | Estratégias de Ensino e Descrição da Atividade | Recursos e Ambientes | Instrumentos de Avaliação |
|---|---|---|---|---|---|
| 01 — 04/08/2026 | Identificar tipos, funções e estratégias de teste; reconhecer métodos de verificação de software; aplicar o ciclo TDD; desenvolver testes unitários; reconhecer boas práticas e antipadrões. | Testes de software; níveis e tipos de testes; TDD; ciclo Red-Green-Refactor; JUnit 5; asserções; anotações; padrão AAA; código limpo; boas práticas; antipadrões. | Aula expositiva dialogada; demonstração ao vivo; prática guiada com JUnit 5; desenvolvimento em duplas; versionamento dos ciclos RED, GREEN e REFACTOR; debate sobre acessibilidade. | Laboratório de Informática; projetor; IDE; Java; Maven ou Gradle; JUnit 5; Git e GitHub. | Ficha de observação; análise dos testes; histórico de commits; execução do projeto; participação no debate; entrega no repositório. |

---

# 2. Objetivos de Aprendizagem

Ao final da aula, o estudante deverá ser capaz de:

- explicar o que é teste de software;
- diferenciar teste unitário, integração, sistema e aceitação;
- explicar o funcionamento do TDD;
- aplicar o ciclo **Red → Green → Refactor**;
- criar testes com JUnit 5;
- utilizar as principais anotações do JUnit;
- utilizar asserções adequadas;
- organizar testes utilizando o padrão AAA;
- reconhecer padrões que tornam o código mais testável;
- identificar boas práticas e antipadrões;
- relacionar qualidade de software com impacto social.

---

# 3. O que é Teste de Software?

Teste de software é o processo de verificar se uma aplicação apresenta o comportamento esperado.

Um teste compara:

```text
Resultado esperado
        ×
Resultado obtido
```

Quando os dois resultados são iguais, o teste passa.

Quando são diferentes, o teste falha.

## Analogia: conferência de uma compra

Imagine que você fez uma compra e recebeu uma nota fiscal.

A nota diz:

```text
2 cadernos
3 canetas
1 mochila
```

Ao abrir a caixa, você confere se os itens recebidos correspondem ao que está registrado.

O teste de software funciona de maneira parecida:

- a regra do sistema representa o pedido;
- o código representa os produtos enviados;
- o teste faz a conferência.

## Exemplo simples

```java
int resultado = 2 + 3;

assertEquals(5, resultado);
```

Nesse exemplo:

- resultado esperado: `5`;
- resultado obtido: valor da variável `resultado`;
- comparação: realizada por `assertEquals`.

---

# 4. Tipos de Testes

Os testes podem ser classificados de diferentes maneiras. Uma das classificações mais utilizadas considera o nível em que o software está sendo verificado.

## 4.1 Teste unitário

O teste unitário verifica uma pequena parte do sistema de forma isolada.

Essa pequena parte pode ser:

- um método;
- uma função;
- uma classe;
- uma regra de negócio.

### Analogia

É como testar uma lâmpada antes de instalá-la em uma casa.

Você não precisa ligar toda a rede elétrica do prédio para descobrir se a lâmpada funciona.

### Exemplo

```java
@Test
void deveSomarDoisNumeros() {
    Calculadora calculadora = new Calculadora();

    int resultado = calculadora.somar(2, 3);

    assertEquals(5, resultado);
}
```

---

## 4.2 Teste de integração

O teste de integração verifica se duas ou mais partes do sistema trabalham corretamente juntas.

Exemplos:

- aplicação e banco de dados;
- serviço e repositório;
- sistema e API externa;
- controller e service.

### Analogia

Não basta testar separadamente o carregador e o celular. Também precisamos verificar se eles funcionam quando conectados.

### Exemplo de cenário

```text
Cadastro de aluno
        ↓
Service valida os dados
        ↓
Repository salva no banco
```

O teste de integração pode verificar todo esse fluxo.

---

## 4.3 Teste de sistema

O teste de sistema verifica a aplicação completa.

Ele analisa o comportamento do sistema como um todo, considerando as funcionalidades integradas.

### Analogia

É como testar um carro completo:

- motor;
- freios;
- direção;
- iluminação;
- painel;
- câmbio.

Cada componente pode funcionar isoladamente, mas o carro precisa funcionar como conjunto.

---

## 4.4 Teste de aceitação

O teste de aceitação verifica se o sistema atende às necessidades do cliente ou usuário.

Ele normalmente é baseado em:

- requisitos;
- critérios de aceite;
- regras de negócio;
- estórias de usuário.

### Exemplo

**Estória de usuário:**

> Como cliente, quero calcular o frete antes de finalizar a compra para saber o valor total do pedido.

**Critério de aceite:**

```text
Dado que o cliente informou um CEP válido,
quando solicitar o cálculo,
então o sistema deve exibir o valor do frete.
```

---

## 4.5 Teste funcional

Verifica o que o sistema faz.

Exemplos:

- cadastrar usuário;
- calcular desconto;
- validar CPF;
- gerar boleto;
- autenticar login.

Pergunta principal:

> A funcionalidade entrega o comportamento esperado?

---

## 4.6 Teste não funcional

Verifica características de qualidade que não representam diretamente uma regra de negócio.

Exemplos:

- desempenho;
- segurança;
- acessibilidade;
- usabilidade;
- compatibilidade;
- confiabilidade.

Perguntas possíveis:

- O sistema suporta muitos usuários?
- A página funciona em diferentes navegadores?
- O conteúdo pode ser utilizado por leitores de tela?
- A senha é armazenada com segurança?
- O tempo de resposta é adequado?

---

## 4.7 Teste de regressão

O teste de regressão verifica se uma alteração que resolveu um problema não quebrou funcionalidades que já funcionavam.

### Analogia

Imagine consertar a torneira da cozinha e, depois do reparo, descobrir que o chuveiro parou de funcionar.

Os testes de regressão verificam se o restante da casa continua funcionando após uma mudança.

No TDD, o conjunto de testes funciona como uma rede de segurança contra regressões.

---

## 4.8 Teste de fumaça — Smoke Test

É um conjunto pequeno de testes que verifica se as principais funcionalidades do sistema estão funcionando.

O objetivo não é testar tudo, mas descobrir rapidamente se a aplicação está minimamente estável.

### Origem da analogia

Depois de montar um equipamento eletrônico, ele era ligado para verificar se soltava fumaça.

Se não queimasse imediatamente, os testes detalhados poderiam continuar.

### Exemplos

- a aplicação inicia;
- o banco conecta;
- a tela de login abre;
- o endpoint principal responde.

---

## 4.9 Teste ponta a ponta — End-to-End

O teste ponta a ponta simula uma jornada completa do usuário.

### Exemplo

```text
Usuário acessa a loja
        ↓
Realiza login
        ↓
Escolhe um produto
        ↓
Adiciona ao carrinho
        ↓
Finaliza a compra
        ↓
Recebe a confirmação
```

Esse teste verifica o fluxo inteiro, normalmente utilizando uma aplicação próxima do ambiente real.

---

## 4.10 Pirâmide de testes

A pirâmide de testes sugere que o projeto possua:

```text
               Poucos testes ponta a ponta
                       /\
                      /  \
             Alguns testes de integração
                    /      \
                   /        \
             Muitos testes unitários
```

Os testes unitários ficam na base porque geralmente são:

- rápidos;
- baratos;
- fáceis de executar;
- simples de localizar quando falham.

Os testes ponta a ponta são importantes, mas costumam ser:

- mais lentos;
- mais complexos;
- mais frágeis;
- mais caros de manter.

---

# 5. O que é TDD?

TDD significa **Test-Driven Development**, ou Desenvolvimento Orientado a Testes.

No TDD, o teste é escrito antes do código de produção.

O processo não começa perguntando:

> Como vou programar essa funcionalidade?

Ele começa perguntando:

> Qual comportamento essa funcionalidade deve apresentar?

O teste descreve esse comportamento antes que a implementação exista.

## TDD não é apenas testar

TDD é também uma prática de:

- análise;
- design;
- desenvolvimento incremental;
- documentação;
- melhoria contínua;
- prevenção de falhas.

O teste funciona como uma especificação executável.

Uma documentação tradicional pode ficar desatualizada. Um teste automatizado precisa continuar passando.

---

# 6. Ciclo Red, Green e Refactor

O ciclo do TDD possui três etapas.

## 6.1 RED — vermelho

Na etapa RED, o desenvolvedor escreve um teste que falha.

A falha demonstra que o comportamento ainda não foi implementado.

```text
Escrever o teste
        ↓
Executar
        ↓
Teste falha
```

### Importante

O teste deve falhar pelo motivo correto.

Por exemplo, ele deve falhar porque a soma está incorreta, e não porque faltou importar uma classe ou porque o projeto não compila.

---

## 6.2 GREEN — verde

Na etapa GREEN, o desenvolvedor escreve o mínimo de código necessário para fazer o teste passar.

```text
Teste falhando
        ↓
Implementação mínima
        ↓
Teste passando
```

Nesta etapa, o código ainda pode ser simples ou pouco elegante.

O objetivo imediato é atender ao comportamento definido pelo teste.

---

## 6.3 REFACTOR — refatorar

Na etapa REFACTOR, o desenvolvedor melhora o código sem alterar o comportamento.

É possível:

- remover duplicações;
- melhorar nomes;
- extrair métodos;
- extrair constantes;
- reduzir estruturas condicionais;
- separar responsabilidades;
- melhorar legibilidade.

Depois de cada alteração, os testes devem continuar passando.

---

## Analogia: escultor

O TDD pode ser comparado ao trabalho de um escultor.

### RED

O escultor define o que deseja criar:

```text
Quero construir uma figura humana.
```

### GREEN

Ele remove a quantidade mínima de material até que a forma básica apareça.

### REFACTOR

Depois, melhora detalhes, proporções e acabamento sem destruir a forma já construída.

---

# 7. Regra de Ouro do TDD

> **Nenhum código de produção é escrito sem antes existir um teste falhando.**

Essa regra significa que todo comportamento novo deve nascer a partir de uma necessidade demonstrada por um teste.

## O que é código de produção?

É o código que implementa o funcionamento real da aplicação.

Exemplo:

```java
public double paraFahrenheit(double celsius) {
    return celsius * 9 / 5 + 32;
}
```

## Primeiro: teste

```java
@Test
void deveConverterZeroCelsiusParaTrintaEDoisFahrenheit() {
    Conversor conversor = new Conversor();

    double resultado = conversor.paraFahrenheit(0);

    assertEquals(32, resultado, 0.01);
}
```

## Depois: implementação

```java
public double paraFahrenheit(double celsius) {
    return celsius * 9 / 5 + 32;
}
```

## Por que o teste deve falhar primeiro?

Porque isso confirma que:

- o teste realmente está sendo executado;
- o comportamento ainda não existe;
- o teste consegue detectar a ausência da funcionalidade;
- a implementação será criada para resolver uma necessidade real.

Um teste que já nasce passando pode ser um teste inútil.

---

# 8. Estrutura de um Teste com JUnit 5

Exemplo:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

    @Test
    void deveSomarDoisNumeros() {
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.somar(2, 3);

        assertEquals(5, resultado);
    }
}
```

## Partes do teste

```java
@Test
```

Indica que o método representa um teste.

```java
void deveSomarDoisNumeros()
```

É o nome do teste. Deve explicar o comportamento esperado.

```java
Calculadora calculadora = new Calculadora();
```

Prepara o objeto necessário.

```java
int resultado = calculadora.somar(2, 3);
```

Executa a ação que será testada.

```java
assertEquals(5, resultado);
```

Compara o resultado esperado com o resultado obtido.

---

# 9. Anotações mais Utilizadas no JUnit 5

As anotações informam ao JUnit como cada método deve ser tratado.

## 9.1 `@Test`

Indica um método de teste.

```java
@Test
void deveSomarDoisNumeros() {
    assertEquals(5, calculadora.somar(2, 3));
}
```

---

## 9.2 `@BeforeEach`

Executa antes de cada teste.

É utilizada para preparar objetos ou dados comuns.

```java
class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void preparar() {
        calculadora = new Calculadora();
    }

    @Test
    void deveSomar() {
        assertEquals(5, calculadora.somar(2, 3));
    }

    @Test
    void deveSubtrair() {
        assertEquals(2, calculadora.subtrair(5, 3));
    }
}
```

### Analogia

É como limpar e organizar a bancada antes de cada experiência de laboratório.

Cada teste começa em um ambiente conhecido.

---

## 9.3 `@AfterEach`

Executa depois de cada teste.

Pode ser utilizada para:

- fechar arquivos;
- encerrar conexões;
- limpar dados temporários;
- restaurar configurações.

```java
@AfterEach
void finalizar() {
    System.out.println("Teste finalizado");
}
```

---

## 9.4 `@BeforeAll`

Executa uma única vez antes de todos os testes da classe.

Por padrão, o método deve ser `static`.

```java
@BeforeAll
static void iniciarTestes() {
    System.out.println("Iniciando a classe de testes");
}
```

Pode ser utilizada para preparar um recurso caro e compartilhado.

---

## 9.5 `@AfterAll`

Executa uma única vez depois de todos os testes da classe.

```java
@AfterAll
static void encerrarTestes() {
    System.out.println("Todos os testes foram finalizados");
}
```

---

## 9.6 `@DisplayName`

Permite definir um nome mais legível para o teste.

```java
@Test
@DisplayName("Deve calcular multa de R$ 6,00 para três dias")
void deveCalcularMulta() {
    assertEquals(6.00, calculadora.calcularMulta(3), 0.01);
}
```

---

## 9.7 `@Disabled`

Desativa temporariamente um teste.

```java
@Test
@Disabled("Funcionalidade ainda não implementada")
void deveEnviarEmail() {
}
```

### Atenção

Um teste desativado não protege o sistema.

Não transforme `@Disabled` em um depósito de testes esquecidos.

---

## 9.8 `@ParameterizedTest`

Permite executar o mesmo teste com diferentes dados.

```java
@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 8})
void deveReconhecerNumeroPar(int numero) {
    assertTrue(verificador.ehPar(numero));
}
```

---

## 9.9 `@ValueSource`

Fornece uma lista simples de valores para um teste parametrizado.

```java
@ParameterizedTest
@ValueSource(strings = {"Ana", "Carlos", "Maria"})
void nomeValidoNaoDeveEstarVazio(String nome) {
    assertFalse(nome.isBlank());
}
```

---

## 9.10 `@CsvSource`

Fornece vários parâmetros por linha.

```java
@ParameterizedTest
@CsvSource({
    "0, 32",
    "100, 212",
    "-40, -40"
})
void deveConverterCelsiusParaFahrenheit(
    double celsius,
    double fahrenheitEsperado
) {
    assertEquals(
        fahrenheitEsperado,
        conversor.paraFahrenheit(celsius),
        0.01
    );
}
```

Cada linha representa um cenário.

---

## 9.11 `@CsvFileSource`

Carrega os dados de um arquivo CSV.

```java
@ParameterizedTest
@CsvFileSource(resources = "/temperaturas.csv", numLinesToSkip = 1)
void deveConverterTemperaturas(
    double celsius,
    double fahrenheitEsperado
) {
    assertEquals(
        fahrenheitEsperado,
        conversor.paraFahrenheit(celsius),
        0.01
    );
}
```

Exemplo de arquivo:

```csv
celsius,fahrenheit
0,32
100,212
-40,-40
```

---

## 9.12 `@MethodSource`

Obtém os parâmetros a partir de um método Java.

```java
static Stream<Arguments> fornecerSomas() {
    return Stream.of(
        Arguments.of(2, 3, 5),
        Arguments.of(-1, 1, 0),
        Arguments.of(10, 5, 15)
    );
}

@ParameterizedTest
@MethodSource("fornecerSomas")
void deveSomar(int numero1, int numero2, int esperado) {
    assertEquals(esperado, calculadora.somar(numero1, numero2));
}
```

É útil quando os dados são mais complexos.

---

## 9.13 `@Nested`

Permite agrupar testes relacionados.

```java
class ValidadorSenhaTest {

    @Nested
    class SenhasValidas {

        @Test
        void deveAceitarSenhaComOitoCaracteres() {
        }
    }

    @Nested
    class SenhasInvalidas {

        @Test
        void deveRejeitarSenhaCurta() {
        }
    }
}
```

---

## 9.14 `@Tag`

Cria categorias de testes.

```java
@Test
@Tag("rapido")
void deveSomar() {
}
```

Possíveis categorias:

- `unitario`;
- `integracao`;
- `rapido`;
- `lento`;
- `banco`;
- `seguranca`.

---

## 9.15 Resumo das anotações

| Anotação | Finalidade |
|---|---|
| `@Test` | Define um método de teste |
| `@BeforeEach` | Executa antes de cada teste |
| `@AfterEach` | Executa depois de cada teste |
| `@BeforeAll` | Executa uma vez antes de todos os testes |
| `@AfterAll` | Executa uma vez depois de todos os testes |
| `@DisplayName` | Define um nome mais amigável |
| `@Disabled` | Desativa temporariamente um teste |
| `@ParameterizedTest` | Executa o teste com diferentes dados |
| `@ValueSource` | Fornece valores simples |
| `@CsvSource` | Fornece parâmetros em formato CSV |
| `@CsvFileSource` | Carrega parâmetros de um arquivo |
| `@MethodSource` | Fornece parâmetros por meio de um método |
| `@Nested` | Agrupa testes relacionados |
| `@Tag` | Classifica os testes |

---

# 10. Principais Asserções

Asserções verificam se o comportamento obtido corresponde ao esperado.

## 10.1 `assertEquals`

Compara igualdade.

```java
assertEquals(5, calculadora.somar(2, 3));
```

Para números decimais, utilize uma margem de tolerância:

```java
assertEquals(32.0, conversor.paraFahrenheit(0), 0.01);
```

---

## 10.2 `assertNotEquals`

Verifica se os valores são diferentes.

```java
assertNotEquals(10, calculadora.somar(2, 3));
```

---

## 10.3 `assertTrue`

Espera que uma condição seja verdadeira.

```java
assertTrue(verificador.ehPar(4));
```

---

## 10.4 `assertFalse`

Espera que uma condição seja falsa.

```java
assertFalse(verificador.ehPar(5));
```

---

## 10.5 `assertNull`

Espera que o resultado seja `null`.

```java
assertNull(repositorio.buscarPorId(999));
```

---

## 10.6 `assertNotNull`

Espera que o resultado não seja `null`.

```java
assertNotNull(repositorio.buscarPorId(1));
```

---

## 10.7 `assertThrows`

Verifica se uma exceção é lançada.

```java
assertThrows(
    IllegalArgumentException.class,
    () -> calculadora.dividir(10, 0)
);
```

---

## 10.8 `assertDoesNotThrow`

Verifica se o código é executado sem lançar exceção.

```java
assertDoesNotThrow(
    () -> cadastro.cadastrar("Ana")
);
```

---

## 10.9 `assertAll`

Agrupa várias verificações.

```java
assertAll(
    () -> assertEquals("Ana", aluno.getNome()),
    () -> assertEquals(18, aluno.getIdade()),
    () -> assertNotNull(aluno.getMatricula())
);
```

Todas as verificações são executadas, mesmo que uma delas falhe.

---

## 10.10 `assertSame` e `assertNotSame`

Comparam referências de objetos.

```java
assertSame(objeto1, objeto2);
assertNotSame(objeto1, objeto3);
```

`assertEquals` verifica igualdade de conteúdo.

`assertSame` verifica se é exatamente o mesmo objeto na memória.

---

# 11. Padrão AAA

AAA significa:

```text
Arrange
Act
Assert
```

Em português:

```text
Preparar
Agir
Verificar
```

## Exemplo

```java
@Test
void deveCalcularDesconto() {
    // Arrange — preparar
    CalculadoraDesconto calculadora = new CalculadoraDesconto();
    double valorCompra = 100.00;

    // Act — agir
    double resultado = calculadora.calcular(valorCompra, 10);

    // Assert — verificar
    assertEquals(90.00, resultado, 0.01);
}
```

## Analogia: receita de bolo

### Arrange

Separar:

- farinha;
- ovos;
- açúcar;
- forma.

### Act

Misturar e assar.

### Assert

Verificar se o bolo:

- cresceu;
- assou;
- ficou com o sabor esperado.

O padrão AAA torna os testes mais legíveis porque separa claramente as etapas.

---

# 12. Padrões de Desenvolvimento para Código Testável

TDD funciona melhor quando o código é organizado de forma testável.

## 12.1 Responsabilidade Única

Uma classe deve possuir uma responsabilidade principal.

### Código difícil de testar

```java
public class PedidoService {

    public void finalizarPedido(Pedido pedido) {
        validarPedido(pedido);
        salvarNoBanco(pedido);
        enviarEmail(pedido);
        gerarNotaFiscal(pedido);
        imprimirComprovante(pedido);
    }
}
```

Essa classe faz muitas coisas.

### Código mais testável

```java
public class PedidoService {

    private final PedidoRepository repository;
    private final EmailService emailService;

    public PedidoService(
        PedidoRepository repository,
        EmailService emailService
    ) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public void finalizar(Pedido pedido) {
        validar(pedido);
        repository.salvar(pedido);
        emailService.enviarConfirmacao(pedido);
    }
}
```

Agora cada componente pode ser testado isoladamente.

---

## 12.2 Injeção de Dependência

Em vez de a classe criar suas próprias dependências, elas são recebidas pelo construtor.

### Dependência criada internamente

```java
public class RelatorioService {

    private BancoDeDados banco = new BancoDeDados();
}
```

Isso dificulta a substituição do banco em um teste.

### Dependência injetada

```java
public class RelatorioService {

    private final Repositorio repositorio;

    public RelatorioService(Repositorio repositorio) {
        this.repositorio = repositorio;
    }
}
```

No teste, podemos fornecer uma implementação controlada.

### Analogia

Imagine uma cafeteira que só aceita um tipo específico de cápsula soldada internamente.

Ela é difícil de adaptar.

Com injeção de dependência, a cafeteira recebe a cápsula externamente e pode trabalhar com diferentes opções compatíveis.

---

## 12.3 Programar para interfaces

A classe depende de um contrato, não de uma implementação específica.

```java
public interface Notificador {
    void enviar(String mensagem);
}
```

Implementações:

```java
public class EmailNotificador implements Notificador {
    public void enviar(String mensagem) {
        System.out.println("Enviando por e-mail");
    }
}
```

```java
public class NotificadorFalso implements Notificador {

    private String mensagemRecebida;

    public void enviar(String mensagem) {
        mensagemRecebida = mensagem;
    }

    public String getMensagemRecebida() {
        return mensagemRecebida;
    }
}
```

Nos testes, usamos o notificador falso sem enviar um e-mail real.

---

## 12.4 Separação entre regra de negócio e infraestrutura

A regra de negócio não deve depender diretamente de:

- banco de dados;
- rede;
- arquivos;
- relógio do sistema;
- serviços externos.

### Exemplo

Em vez de consultar diretamente a data atual:

```java
LocalDate hoje = LocalDate.now();
```

podemos receber a data:

```java
public boolean estaVencido(LocalDate vencimento, LocalDate hoje) {
    return vencimento.isBefore(hoje);
}
```

Isso torna o teste previsível.

---

## 12.5 Objetos pequenos e métodos pequenos

Métodos pequenos:

- são mais fáceis de compreender;
- possuem menos caminhos;
- são mais fáceis de testar;
- facilitam a manutenção.

### Método extenso

```java
public void processarPedido() {
    // valida
    // calcula
    // salva
    // envia
    // registra
    // imprime
}
```

### Refatoração

```java
public void processarPedido() {
    validar();
    calcularTotal();
    salvar();
    notificar();
}
```

---

## 12.6 Padrão Factory para criação de dados de teste

Quando muitos testes precisam criar objetos semelhantes, podemos centralizar sua criação.

```java
public class AlunoFactory {

    public static Aluno criarAlunoValido() {
        return new Aluno(
            "Ana",
            "ana@email.com",
            18
        );
    }
}
```

No teste:

```java
Aluno aluno = AlunoFactory.criarAlunoValido();
```

Isso evita duplicação.

---

## 12.7 Test Data Builder

É um padrão utilizado para criar objetos de teste com diferentes configurações.

```java
public class AlunoBuilder {

    private String nome = "Ana";
    private String email = "ana@email.com";
    private int idade = 18;

    public AlunoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public AlunoBuilder comIdade(int idade) {
        this.idade = idade;
        return this;
    }

    public Aluno construir() {
        return new Aluno(nome, email, idade);
    }
}
```

Uso:

```java
Aluno aluno = new AlunoBuilder()
    .comNome("Carlos")
    .comIdade(20)
    .construir();
```

---

# 13. Boas Práticas em TDD e Testes

## 13.1 Um comportamento por teste

Cada teste deve verificar um comportamento principal.

Evite um teste que tente validar:

- cadastro;
- login;
- pagamento;
- envio de e-mail;
- geração de relatório.

Quando ele falhar, será difícil descobrir a causa.

---

## 13.2 Nome descritivo

Evite:

```java
@Test
void teste1() {
}
```

Prefira:

```java
@Test
void deveRejeitarCpfComMenosDeOnzeDigitos() {
}
```

O nome do teste deve funcionar como uma pequena documentação.

---

## 13.3 Testes independentes

Um teste não deve depender do resultado de outro.

Errado:

```text
Teste 1 cadastra o usuário
Teste 2 depende do usuário criado pelo teste 1
Teste 3 depende do resultado do teste 2
```

Correto:

```text
Cada teste prepara seus próprios dados.
```

---

## 13.4 Testes determinísticos

Um teste determinístico sempre produz o mesmo resultado nas mesmas condições.

Evite depender diretamente de:

- hora atual;
- números aleatórios;
- internet;
- ordem de execução;
- banco compartilhado;
- arquivos que podem mudar.

### Teste instável

```java
@Test
void deveGerarNumeroMenorQueCinco() {
    int numero = new Random().nextInt(10);

    assertTrue(numero < 5);
}
```

Esse teste pode passar ou falhar sem alteração no código.

---

## 13.5 Testes rápidos

Testes unitários devem executar rapidamente.

Quanto mais rápidos, maior a chance de serem executados com frequência.

Um conjunto lento de testes vira aquele aparelho de academia comprado em janeiro: existe, ocupa espaço, mas ninguém usa.

---

## 13.6 Executar os testes frequentemente

No TDD, os testes são executados após pequenas alterações.

```text
Alterou
   ↓
Executou
   ↓
Confirmou
```

Não espere escrever cinquenta linhas para descobrir que o problema começou na terceira.

---

## 13.7 Testar comportamento, não implementação

Evite testar detalhes internos que podem mudar durante uma refatoração.

O importante é verificar a saída observável.

### Comportamento

```java
assertEquals(90.00, calculadora.calcular(100, 10), 0.01);
```

### Detalhe interno

```text
Verificar se a classe chamou exatamente três métodos privados.
```

Métodos privados são detalhes internos.

---

## 13.8 Utilizar dados significativos

Prefira valores que ajudem a entender a regra.

```java
assertEquals(6.00, multa.calcular(3), 0.01);
```

É fácil perceber:

```text
3 dias × R$ 2,00 = R$ 6,00
```

---

## 13.9 Testar limites

Erros aparecem frequentemente nas bordas.

Exemplo: idade mínima de 18 anos.

Teste:

- `17`;
- `18`;
- `19`.

Exemplo: multa não pode ser negativa.

Teste:

- `-1`;
- `0`;
- `1`.

---

## 13.10 Manter testes legíveis

O teste também é código e precisa de qualidade.

Utilize:

- nomes claros;
- poucos dados;
- padrão AAA;
- constantes quando necessário;
- métodos auxiliares;
- builders e factories;
- comentários apenas quando realmente ajudam.

---

## 13.11 Refatorar somente com testes verdes

Antes de refatorar, confirme que todos os testes passam.

Refatorar com testes falhando é como trocar o motor de um carro enquanto ele desce uma ladeira. Pode até dar certo, mas a emoção não está no plano pedagógico.

---

# 14. Antipadrões

Antipadrões são práticas recorrentes que parecem soluções, mas produzem problemas.

## 14.1 Teste que nunca falha

```java
@Test
void deveSomar() {
    assertTrue(true);
}
```

Esse teste não verifica o comportamento da calculadora.

É um alarme que nunca toca, mesmo quando há incêndio.

---

## 14.2 Testar depois e chamar de TDD

Fluxo incorreto:

```text
Implementa tudo
      ↓
Escreve os testes
```

Isso pode ser teste automatizado, mas não é TDD.

No TDD:

```text
Teste falha
      ↓
Implementação
```

---

## 14.3 Teste gigante

```java
@Test
void deveTestarTodoOSistema() {
    // cadastra
    // autentica
    // compra
    // paga
    // envia e-mail
    // gera relatório
}
```

Problemas:

- difícil de entender;
- difícil de manter;
- difícil de localizar falhas;
- lento;
- frágil.

---

## 14.4 Muitos `asserts` sem relação

```java
@Test
void testeSistema() {
    assertEquals(...);
    assertTrue(...);
    assertFalse(...);
    assertNotNull(...);
    assertEquals(...);
}
```

Várias asserções são aceitáveis quando verificam o mesmo comportamento ou objeto.

O problema ocorre quando o teste mistura responsabilidades diferentes.

---

## 14.5 Dependência entre testes

```text
Teste B só passa se Teste A for executado antes.
```

Isso torna o conjunto de testes imprevisível.

Cada teste deve preparar seus próprios dados.

---

## 14.6 Uso excessivo de `@Disabled`

```java
@Disabled
@Test
void funcionalidadeImportante() {
}
```

Um teste desativado não protege nada.

Se o teste não pode ser corrigido imediatamente, registre o motivo e uma tarefa para tratá-lo.

---

## 14.7 Teste frágil

Um teste frágil quebra por mudanças que não alteram o comportamento.

Exemplo:

```text
O teste espera exatamente a mensagem:
"Usuário cadastrado com sucesso!"

A equipe altera para:
"Cadastro realizado com sucesso!"
```

Se o texto não faz parte do requisito, o teste está acoplado a um detalhe desnecessário.

---

## 14.8 Teste misterioso

```java
@Test
void deveCalcular() {
    assertEquals(73.46, servico.calcular(4, 7, 2), 0.01);
}
```

Não está claro:

- o que representam os números;
- qual regra está sendo testada;
- por que o resultado é `73.46`.

Prefira variáveis descritivas.

```java
double valorProduto = 100.00;
double percentualDesconto = 10.00;

double resultado = calculadora.aplicarDesconto(
    valorProduto,
    percentualDesconto
);

assertEquals(90.00, resultado, 0.01);
```

---

## 14.9 Lógica dentro do teste

Evite reproduzir a mesma regra do código de produção dentro do teste.

```java
double esperado = valor * percentual / 100;
assertEquals(esperado, calculadora.calcular(valor, percentual));
```

Se a fórmula estiver errada nos dois lugares, o teste passa.

Prefira um valor esperado explícito:

```java
assertEquals(10.00, calculadora.calcular(100, 10), 0.01);
```

---

## 14.10 Código de produção criado “por garantia”

```text
Talvez um dia precisemos...
```

No TDD, o código deve nascer de um comportamento necessário.

Isso ajuda a evitar o princípio YAGNI:

> You Aren't Gonna Need It — Você provavelmente não vai precisar disso agora.

---

## 14.11 Testar métodos privados diretamente

Métodos privados são detalhes de implementação.

Eles devem ser testados por meio do comportamento público da classe.

Se um método privado é tão complexo que precisa ser testado isoladamente, talvez ele represente uma responsabilidade que deveria estar em outra classe.

---

## 14.12 Ignorar um teste vermelho

Um teste vermelho deve ser tratado imediatamente.

Continuar implementando com testes falhando aumenta a quantidade de causas possíveis.

É como ignorar uma luz vermelha no painel do carro e aumentar o volume do rádio. O barulho some; o problema, não.

---

# 15. Exemplo Completo — Calculadora

## 15.1 RED

Primeiro escrevemos o teste.

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculadoraTest {

    @Test
    void deveSomarDoisNumeros() {
        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.somar(2, 3);

        assertEquals(5, resultado);
    }
}
```

O teste falha porque a classe ou o método ainda não existe.

---

## 15.2 GREEN

Criamos o mínimo necessário.

```java
public class Calculadora {

    public int somar(int numero1, int numero2) {
        return numero1 + numero2;
    }
}
```

O teste passa.

---

## 15.3 Novo RED

Criamos um novo comportamento.

```java
@Test
void deveSubtrairDoisNumeros() {
    Calculadora calculadora = new Calculadora();

    int resultado = calculadora.subtrair(5, 3);

    assertEquals(2, resultado);
}
```

---

## 15.4 Novo GREEN

```java
public int subtrair(int numero1, int numero2) {
    return numero1 - numero2;
}
```

---

## 15.5 REFACTOR

Podemos utilizar `@BeforeEach`.

```java
class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void preparar() {
        calculadora = new Calculadora();
    }

    @Test
    void deveSomarDoisNumeros() {
        assertEquals(5, calculadora.somar(2, 3));
    }

    @Test
    void deveSubtrairDoisNumeros() {
        assertEquals(2, calculadora.subtrair(5, 3));
    }
}
```

O comportamento não mudou. Apenas removemos duplicação.

---

# 16. Exemplo Completo — Multa de Biblioteca

## Regra de negócio

A biblioteca cobra:

```text
R$ 2,00 por dia de atraso.
```

A multa não pode ser negativa.

---

## 16.1 Primeiro RED

```java
@Test
void deveCalcularDoisReaisParaUmDiaDeAtraso() {
    CalculadoraMulta calculadora = new CalculadoraMulta();

    double resultado = calculadora.calcularMulta(1);

    assertEquals(2.00, resultado, 0.01);
}
```

---

## 16.2 GREEN

```java
public class CalculadoraMulta {

    public double calcularMulta(int diasAtraso) {
        return 2.00;
    }
}
```

Esse código é simples e faz o primeiro teste passar.

Ele não precisa resolver todos os cenários ainda.

---

## 16.3 Segundo RED

```java
@Test
void deveCalcularSeisReaisParaTresDiasDeAtraso() {
    CalculadoraMulta calculadora = new CalculadoraMulta();

    double resultado = calculadora.calcularMulta(3);

    assertEquals(6.00, resultado, 0.01);
}
```

O código anterior falha.

---

## 16.4 Novo GREEN

```java
public class CalculadoraMulta {

    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 2.00;
    }
}
```

---

## 16.5 Terceiro RED

```java
@Test
void multaNaoDeveSerNegativa() {
    CalculadoraMulta calculadora = new CalculadoraMulta();

    double resultado = calculadora.calcularMulta(-3);

    assertEquals(0.00, resultado, 0.01);
}
```

---

## 16.6 Novo GREEN

```java
public class CalculadoraMulta {

    public double calcularMulta(int diasAtraso) {
        if (diasAtraso < 0) {
            return 0.00;
        }

        return diasAtraso * 2.00;
    }
}
```

---

## 16.7 REFACTOR

```java
public class CalculadoraMulta {

    private static final double VALOR_POR_DIA = 2.00;

    public double calcularMulta(int diasAtraso) {
        int diasValidos = Math.max(diasAtraso, 0);

        return diasValidos * VALOR_POR_DIA;
    }
}
```

Os testes continuam passando.

---

# 17. Testes Parametrizados

Quando o mesmo comportamento precisa ser testado com vários valores, utilizamos testes parametrizados.

## Exemplo com multa

```java
@ParameterizedTest
@CsvSource({
    "0, 0.00",
    "1, 2.00",
    "3, 6.00",
    "10, 20.00",
    "-1, 0.00"
})
void deveCalcularMulta(
    int diasAtraso,
    double multaEsperada
) {
    CalculadoraMulta calculadora = new CalculadoraMulta();

    double resultado = calculadora.calcularMulta(diasAtraso);

    assertEquals(multaEsperada, resultado, 0.01);
}
```

## Benefício

Em vez de criar cinco métodos quase iguais, declaramos os diferentes cenários em uma tabela.

---

# 18. Testes de Exceção

Algumas regras exigem que o sistema rejeite entradas inválidas.

## Exemplo: divisão por zero

```java
public double dividir(double numero1, double numero2) {
    if (numero2 == 0) {
        throw new IllegalArgumentException(
            "O divisor não pode ser zero"
        );
    }

    return numero1 / numero2;
}
```

Teste:

```java
@Test
void deveLancarExcecaoAoDividirPorZero() {
    Calculadora calculadora = new Calculadora();

    IllegalArgumentException excecao = assertThrows(
        IllegalArgumentException.class,
        () -> calculadora.dividir(10, 0)
    );

    assertEquals(
        "O divisor não pode ser zero",
        excecao.getMessage()
    );
}
```

O teste verifica:

- o tipo da exceção;
- a mensagem apresentada.

---

# 19. Testes com Objetos e Listas

## Exemplo com aluno

```java
@Test
void deveCriarAlunoComDadosInformados() {
    Aluno aluno = new Aluno(
        "Ana",
        18,
        "Desenvolvimento de Sistemas"
    );

    assertAll(
        () -> assertEquals("Ana", aluno.getNome()),
        () -> assertEquals(18, aluno.getIdade()),
        () -> assertEquals(
            "Desenvolvimento de Sistemas",
            aluno.getCurso()
        )
    );
}
```

## Exemplo com lista

```java
@Test
void deveAdicionarAlunoNaLista() {
    Turma turma = new Turma();
    Aluno aluno = new Aluno("Ana", 18, "DS");

    turma.adicionar(aluno);

    assertEquals(1, turma.getAlunos().size());
    assertTrue(turma.getAlunos().contains(aluno));
}
```

---

# 20. Atividades Práticas

## Atividade 1 — Analisando cenários

Responda:

### a)

Um desenvolvedor implementou a funcionalidade, executou o sistema e somente depois escreveu o teste.

Ele está seguindo TDD? Justifique.

### b)

Ordene corretamente:

```text
Refatorar
Escrever um teste que falha
Implementar o mínimo para o teste passar
```

### c)

Crie uma analogia autoral para explicar por que o teste deve ser escrito antes do código.

### d)

Responda:

- Em qual fase o código pode estar simples ou pouco elegante?
- Em qual fase os testes funcionam como rede de segurança?
- Por que o teste precisa falhar antes da implementação?

### e)

Classifique cada situação:

```text
Teste unitário
Teste de integração
Teste de sistema
Teste de aceitação
```

1. Verificar se o método `somar()` retorna `5` para `2 + 3`.
2. Verificar se o cadastro salva corretamente no banco.
3. Verificar se toda a aplicação permite realizar uma compra.
4. Verificar se os critérios de aceite da estória foram atendidos.

---

## Atividade 2 — Primeiro ciclo TDD

### Contexto

A biblioteca da escola precisa calcular multas de atraso.

### Regras

- R$ 2,00 por dia;
- zero dias gera multa zero;
- dias negativos não podem gerar multa negativa.

### Etapas

1. Criar o projeto Java.
2. Configurar JUnit 5.
3. Criar o repositório Git.
4. Escrever o primeiro teste.
5. Executar e confirmar o RED.
6. Implementar o mínimo.
7. Confirmar o GREEN.
8. Criar novos testes.
9. Refatorar.
10. Executar todos os testes.

### Commits sugeridos

```text
RED: cria teste para um dia de atraso
GREEN: implementa valor mínimo da multa
RED: adiciona teste para três dias
GREEN: calcula multa por quantidade de dias
RED: adiciona teste para dias negativos
GREEN: impede multa negativa
REFACTOR: extrai constante de valor por dia
```

---

## Atividade 3 — Validação de idade

### Regra

Uma pessoa pode realizar cadastro adulto quando possui 18 anos ou mais.

### Casos obrigatórios

- 17 anos → inválido;
- 18 anos → válido;
- 19 anos → válido;
- idade negativa → deve lançar exceção.

Aplicar:

```text
RED → GREEN → REFACTOR
```

---

## Atividade 4 — Validação de CPF

### Estória de usuário

> Como usuário do sistema, quero validar meu CPF para evitar cadastros com dados incorretos.

### Critérios de aceite

- deve possuir 11 dígitos;
- deve aceitar CPF formatado;
- deve rejeitar todos os dígitos iguais;
- deve validar os dígitos verificadores;
- deve rejeitar valor nulo ou vazio.

### Orientação

Crie um teste para cada critério.

Não tente resolver todos os critérios de uma só vez.

---

## Atividade 5 — Classificação de testes

Considere um sistema de matrícula.

Classifique:

1. Testar o método que calcula a média.
2. Testar o service junto com o repository.
3. Testar o fluxo completo de matrícula.
4. Validar se a estória atende ao critério do cliente.
5. Verificar se a página funciona com leitor de tela.
6. Verificar se o sistema suporta mil acessos simultâneos.

---

## Atividade 6 — Caça aos antipadrões

Analise:

```java
@Test
void teste1() {
    Calculadora calculadora = new Calculadora();

    assertTrue(true);
    assertEquals(5, calculadora.somar(2, 3));
    assertEquals(2, calculadora.subtrair(5, 3));
    assertEquals(6, calculadora.multiplicar(2, 3));
}
```

Responda:

- O nome do teste é adequado?
- O `assertTrue(true)` possui utilidade?
- Quantos comportamentos estão sendo testados?
- Como dividir esse teste?
- Qual padrão pode ser utilizado para organizar cada teste?

---

# 21. Desafio de Acessibilidade

## Contexto

Abreviações comuns em mensagens podem ser lidas de forma inadequada por sintetizadores de voz.

Exemplos:

```text
vc
pq
td
```

A aplicação deve expandir:

| Abreviação | Palavra |
|---|---|
| `vc` | `você` |
| `pq` | `porque` |
| `td` | `tudo` |

## Casos de teste

- `vc está bem` → `você está bem`;
- `não fui pq estava doente` → `não fui porque estava doente`;
- `td ficará bem` → `tudo ficará bem`;
- texto sem abreviação permanece igual;
- `Vc` deve virar `Você`;
- `VC` deve virar `VOCÊ`;
- pontuação deve ser preservada;
- abreviações dentro de outras palavras não devem ser alteradas.

## Exemplo de teste

```java
@Test
void deveSubstituirVcPorVoce() {
    ExpansorAbreviacoes expansor =
        new ExpansorAbreviacoes();

    String resultado =
        expansor.expandir("vc está bem");

    assertEquals("você está bem", resultado);
}
```

## Implementação esperada

Utilizar:

```java
Map<String, String>
```

Exemplo:

```java
Map<String, String> dicionario = Map.of(
    "vc", "você",
    "pq", "porque",
    "td", "tudo"
);
```

## Debate

Responder:

1. Como um leitor de tela pode interpretar uma abreviação?
2. Por que palavras completas podem melhorar a compreensão?
3. Qual a relação entre essa solução e acessibilidade?
4. Como uma pequena decisão técnica pode gerar impacto social?
5. O sistema deveria corrigir o texto original ou apenas preparar uma versão acessível?

> Pequenos gestos técnicos podem gerar impacto social real.

---

# 22. Critérios de Avaliação

| Critério | Pontuação |
|---|---:|
| Compreensão do ciclo TDD | 1,5 |
| Criação dos testes antes da implementação | 1,5 |
| Testes com nomes claros | 1,0 |
| Uso adequado das asserções | 1,0 |
| Aplicação do padrão AAA | 1,0 |
| Implementação mínima no GREEN | 1,0 |
| Refatoração sem quebrar testes | 1,0 |
| Organização e legibilidade | 0,5 |
| Commits RED, GREEN e REFACTOR | 0,5 |
| Relação entre tecnologia e acessibilidade | 1,0 |
| **Total** | **10,0** |

---

# 23. Checklist da Aula

Antes de finalizar, verifique:

- [ ] Sei explicar o que é teste de software.
- [ ] Sei diferenciar teste unitário, integração, sistema e aceitação.
- [ ] Sei explicar o que é TDD.
- [ ] Sei aplicar RED, GREEN e REFACTOR.
- [ ] Sei utilizar `@Test`.
- [ ] Sei utilizar `@BeforeEach`.
- [ ] Sei criar um teste parametrizado.
- [ ] Sei utilizar `assertEquals`.
- [ ] Sei testar exceções com `assertThrows`.
- [ ] Sei organizar um teste com AAA.
- [ ] Sei identificar um teste frágil.
- [ ] Sei reconhecer dependência entre testes.
- [ ] Sei explicar por que os testes devem ser independentes.
- [ ] Sei relacionar testes com qualidade e acessibilidade.
- [ ] Registrei os ciclos do TDD no Git.

---

# 24. Referências

- BECK, Kent. **Test-Driven Development: By Example**. Boston: Addison-Wesley, 2003.
- BECK, Kent. **Extreme Programming Explained: Embrace Change**. Addison-Wesley.
- MARTIN, Robert C. **Código Limpo: Habilidades Práticas do Agile Software**. Alta Books.
- MARTIN, Robert C. **Agile Software Development: Principles, Patterns, and Practices**. Pearson.
- MESZAROS, Gerard. **xUnit Test Patterns: Refactoring Test Code**. Addison-Wesley.
- SENAI DN; SENAI SC. **Teste de Sistema**. Brasília: SENAI DN, 2021.
- ROCHA, Anne Caroline. **Simplificando Teste de Software**. Clube de Autores, 2023.
- JUNIT TEAM. **JUnit 5 User Guide**.

---

## Encerramento

No TDD, testar não é a última etapa.

O teste participa da criação da solução desde o início.

```text
Pensar no comportamento
          ↓
Criar o teste
          ↓
Ver a falha
          ↓
Implementar o mínimo
          ↓
Confirmar o resultado
          ↓
Melhorar com segurança
```

> Um bom teste não demonstra apenas que o código funciona. Ele deixa claro o que o código deve fazer.

---

*Professora Karize Viecelli — SENAI/SC — 2026*
