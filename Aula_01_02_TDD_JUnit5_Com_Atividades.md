# Aula 01 e 02 — TDD, JUnit 5 e Testes Unitários

> Material didático para 1–2 aulas (8 horas)

## Objetivos

Ao final destas aulas o estudante deverá ser capaz de:

- compreender o papel dos testes;
- diferenciar os principais tipos de testes;
- aplicar o ciclo **Red → Green → Refactor**;
- utilizar as principais anotações do JUnit 5;
- escrever testes unitários legíveis;
- reconhecer boas práticas e antipadrões.

---

# 1. O que é um teste?

Um teste automatizado é um programa que verifica se outro programa está funcionando corretamente.

## Analogia

Imagine um professor corrigindo provas.

- O **gabarito** representa o resultado esperado.
- A **prova do aluno** representa o resultado obtido.
- A **correção** compara os dois.

Os testes fazem exatamente isso.

## Exemplo comentado

```java
// Importa o método assertEquals
import static org.junit.jupiter.api.Assertions.assertEquals;

// Importa a anotação de teste
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    // Este método será executado pelo JUnit
    @Test
    void deveSomarDoisNumeros() {

        // Cria a calculadora
        Calculadora calculadora = new Calculadora();

        // Executa a operação
        int resultado = calculadora.somar(2, 3);

        // Verifica o resultado esperado
        assertEquals(5, resultado);
    }
}
```

### Resumo

- Teste possui entrada, execução e comparação.
- O resultado esperado deve estar explícito.

### Fixação

1. Quem representa o "gabarito" em um teste?
2. O que faz o `assertEquals()`?
3. O que acontece quando o resultado é diferente do esperado?

> **Gabarito**
> 1. O valor esperado informado na asserção.
> 2. Compara esperado e obtido.
> 3. O teste falha.

---

# 2. Tipos de testes

| Tipo | O que verifica |
|------|----------------|
| Unitário | Um método ou classe |
| Integração | Comunicação entre componentes |
| Sistema | Aplicação completa |
| Aceitação | Requisitos do cliente |
| Regressão | Se algo antigo quebrou |

## Analogia

Construir uma bicicleta:

- Unitário → testar o freio.
- Integração → montar freio + roda.
- Sistema → pedalar.
- Aceitação → o cliente aprova.
- Regressão → trocar o banco sem estragar os freios.

### Fixação

Classifique:

- testar `somar()` → ______
- testar API + banco → ______
- testar compra completa → ______

> **Gabarito**
>
> Unitário, Integração e Sistema.

---

# 3. O TDD

## Regra de ouro

> Nenhum código de produção é escrito sem antes existir um teste falhando.

## Ciclo

```text
🔴 RED
Escrever um teste

↓

🟢 GREEN
Implementar o mínimo

↓

🔵 REFACTOR
Melhorar o código
```

### Analogia

É como montar um móvel utilizando o manual.

Primeiro você lê o passo, depois executa e somente então melhora o acabamento.

### Fixação

Ordene:

- Green
- Red
- Refactor

> **Gabarito**
>
> Red → Green → Refactor

---

# 4. Anotações mais utilizadas

| Anotação | Uso |
|-----------|-----|
| `@Test` | Método de teste |
| `@BeforeEach` | Executa antes de cada teste |
| `@AfterEach` | Executa depois |
| `@BeforeAll` | Uma vez antes |
| `@AfterAll` | Uma vez depois |
| `@ParameterizedTest` | Mesmo teste com vários dados |
| `@CsvSource` | Dados em tabela |
| `@DisplayName` | Nome amigável |

## Exemplo comentado

```java
class CalculadoraTest {

    private Calculadora calculadora;

    // Executa antes de cada teste
    @BeforeEach
    void preparar() {

        // Cria uma nova calculadora
        calculadora = new Calculadora();
    }

    @Test
    void deveSomar() {

        // Executa a operação
        int resultado = calculadora.somar(2,3);

        // Verifica
        assertEquals(5, resultado);
    }
}
```

### Fixação

Explique a diferença entre:

- `@BeforeEach`
- `@BeforeAll`

> **Gabarito**
>
> BeforeEach executa antes de cada teste; BeforeAll apenas uma vez.

---

# 5. Asserções

| Método | Objetivo |
|---------|----------|
| assertEquals | Igualdade |
| assertTrue | Verdadeiro |
| assertFalse | Falso |
| assertNull | Nulo |
| assertNotNull | Não nulo |
| assertThrows | Exceção |

### Exercício

Complete:

```java
@Test
void deveMultiplicar(){

    Calculadora calc = new Calculadora();

    int resultado = calc.multiplicar(3,4);

    // complete
}
```

> **Gabarito**

```java
assertEquals(12, resultado);
```

---

# 6. Teste Parametrizado

```java
// Executa o mesmo teste com vários valores
@ParameterizedTest

// Cada linha representa um cenário
@CsvSource({
    "0,32",
    "100,212",
    "-40,-40"
})
void deveConverter(
        double celsius,
        double fahrenheit){

    // Cria o objeto
    Conversor conversor = new Conversor();

    // Executa o método
    double resultado =
        conversor.paraFahrenheit(celsius);

    // Compara esperado e obtido
    assertEquals(
        fahrenheit,
        resultado,
        0.01
    );
}
```

### Fixação

Qual vantagem de um teste parametrizado?

> **Gabarito**
>
> Evita repetição e permite testar vários cenários.

---

# 7. Boas práticas

- Um comportamento por teste.
- Nomes descritivos.
- Testes independentes.
- Código organizado em AAA.
- Executar frequentemente.
- Refatorar somente com testes verdes.

### Exercício

Marque as boas práticas:

- ( ) assertTrue(true)
- ( ) Testes independentes
- ( ) Um comportamento por teste
- ( ) Nome teste1()

> **Gabarito**

Somente:
- Testes independentes
- Um comportamento por teste

---

# 8. Antipadrões

## Exemplo

```java
@Test
void teste1(){

    Calculadora c = new Calculadora();

    assertTrue(true);

    assertEquals(5,c.somar(2,3));
    assertEquals(2,c.subtrair(5,3));
    assertEquals(6,c.multiplicar(2,3));

}
```

### Perguntas

- O nome está bom?
- Quantos comportamentos existem?
- O assertTrue(true) ajuda?

> **Gabarito**

- Não.
- Três comportamentos.
- Não possui utilidade.

---

# 9. Desafio prático

Implemente TDD para calcular multa da biblioteca.

## Regras

- R$ 2,00 por dia.
- Nunca negativa.

Faça:

1. RED
2. GREEN
3. REFACTOR

Realize commits separados.

---

# Checklist

- [ ] Sei explicar TDD.
- [ ] Sei explicar RED GREEN REFACTOR.
- [ ] Sei criar um teste.
- [ ] Sei usar @Test.
- [ ] Sei usar @BeforeEach.
- [ ] Sei usar assertEquals.
- [ ] Sei criar teste parametrizado.
- [ ] Sei identificar antipadrões.
