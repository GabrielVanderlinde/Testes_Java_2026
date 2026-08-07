# Aula 05 — Testes Parametrizados

**Módulo:** 2 — JUnit 5 na Prática
**Carga horária:** 4 horas
**Professor(a):** [Nome/@handle da turma]

---

## 🎯 Objetivos da aula

- Escrever testes parametrizados com `@ParameterizedTest`;
- Usar `@ValueSource`, `@CsvSource` e `@MethodSource` para fornecer múltiplos conjuntos de dados a um mesmo teste;
- Testar timeouts com `@Timeout`;
- Reduzir duplicação de código em testes que só variam os dados de entrada.

---

## 🖼️ Retomando a analogia — múltiplas testemunhas, a mesma pergunta

Às vezes, o tribunal precisa ouvir **várias testemunhas diferentes respondendo exatamente à mesma pergunta**. Em vez de repetir a audiência inteira para cada uma, o JUnit nos dá o `@ParameterizedTest`: uma única "pergunta" (o corpo do teste), aplicada a vários "depoimentos" (os dados de entrada) de uma só vez.

---

## 📚 Conteúdo teórico

### 1. O problema: testes repetidos

```java
// Sem parametrização: três testes praticamente idênticos
@Test
void validarEmail1() { assertTrue(Validador.emailValido("a@a.com")); }

@Test
void validarEmail2() { assertTrue(Validador.emailValido("b@b.com")); }

@Test
void validarEmail3() { assertTrue(Validador.emailValido("c@c.com")); }
```

### 2. `@ParameterizedTest` + `@ValueSource`

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@ParameterizedTest
@ValueSource(strings = {"a@a.com", "b@b.com", "c@c.com"})
void emailsValidosDevemPassarNaValidacao(String email) {
    assertTrue(Validador.emailValido(email));
}
```

`@ValueSource` aceita listas simples: `strings`, `ints`, `doubles`, `booleans`, etc. — um valor por execução do teste.

### 3. `@CsvSource` — múltiplos parâmetros por execução

Quando precisamos de **mais de um valor por caso**, usamos `@CsvSource`:

```java
import org.junit.jupiter.params.provider.CsvSource;

@ParameterizedTest
@CsvSource({
    "2, 3, 5",
    "10, 5, 15",
    "-1, 1, 0"
})
void somaDeveRetornarResultadoCorreto(int a, int b, int esperado) {
    assertEquals(esperado, Calculadora.somar(a, b));
}
```

Cada linha do `@CsvSource` vira uma execução do teste, com os valores separados por vírgula mapeados na ordem dos parâmetros do método.

### 4. `@MethodSource` — quando os dados são complexos

Para casos mais elaborados (ou objetos), usamos um método que fornece os dados:

```java
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

@ParameterizedTest
@MethodSource("fornecerNotasEConceitos")
void calcularConceitoDeveRetornarValorCorreto(double nota, String conceitoEsperado) {
    assertEquals(conceitoEsperado, Boletim.calcularConceito(nota));
}

// Método "fornecedor": mesmo nome referenciado no @MethodSource
static Stream<org.junit.jupiter.params.provider.Arguments> fornecerNotasEConceitos() {
    return Stream.of(
        org.junit.jupiter.params.provider.Arguments.of(9.5, "Excelente"),
        org.junit.jupiter.params.provider.Arguments.of(7.0, "Bom"),
        org.junit.jupiter.params.provider.Arguments.of(4.0, "Insuficiente")
    );
}
```

### 5. Testando tempo de execução com `@Timeout`

```java
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

@Test
@Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
void calculoNaoDeveDemorarMaisQue200ms() {
    Calculadora.operacaoPesada();
    // Se demorar mais que 200ms, o teste falha automaticamente
}
```

---

<a id="atividade"></a>
## 💻 Atividade Prática

**Duração sugerida:** 70 minutos

### Passo a passo

1. Crie a classe utilitária `Validador` com o método estático `boolean cpfValido(String cpf)`, que retorna `true` apenas se a String tiver exatamente 11 caracteres numéricos (validação simplificada — não precisa calcular dígito verificador real);
2. Crie a classe `Calculadora` com o método estático `int dobrar(int numero)`, que retorna o número multiplicado por 2;
3. Escreva `ValidadorTest` com um `@ParameterizedTest` usando `@ValueSource` para testar pelo menos 3 CPFs válidos (11 dígitos);
4. Escreva outro `@ParameterizedTest` usando `@CsvSource` para `dobrar()`, testando pelo menos 4 combinações de entrada/saída;
5. Adicione um teste com `@Timeout` de 100ms para o método `dobrar()`.

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito

```java
package br.edu.testesistemas.util;

public class Validador {
    public static boolean cpfValido(String cpf) {
        return cpf != null && cpf.length() == 11 && cpf.chars().allMatch(Character::isDigit);
    }
}
```

```java
package br.edu.testesistemas.util;

public class Calculadora {
    public static int dobrar(int numero) {
        return numero * 2;
    }
}
```

```java
package br.edu.testesistemas.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorTest {

    @ParameterizedTest
    @ValueSource(strings = {"12345678901", "98765432100", "00000000000"})
    void cpfsComOnzeDigitosDevemSerValidos(String cpf) {
        assertTrue(Validador.cpfValido(cpf));
    }
}

class CalculadoraTest {

    @ParameterizedTest
    @CsvSource({
        "1, 2",
        "2, 4",
        "5, 10",
        "-3, -6"
    })
    void dobrarDeveMultiplicarPorDois(int entrada, int esperado) {
        assertEquals(esperado, Calculadora.dobrar(entrada));
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void dobrarDeveExecutarRapidamente() {
        Calculadora.dobrar(10);
    }
}
```

---

**Próxima aula:** vamos organizar melhor nossas classes de teste com `@DisplayName`, `@Nested` e `@Tag`, aproveitando para revisar herança e polimorfismo — pilares de POO que voltam a aparecer com força a partir daqui.
