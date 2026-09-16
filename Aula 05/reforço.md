
# 🧪 Atividade Prática — Testes Parametrizados com JUnit 5

## 🎯 Objetivo

Nesta atividade você desenvolverá **vários testes parametrizados com JUnit 5**.

Um teste parametrizado permite executar o **mesmo método de teste várias vezes**, alterando apenas os dados utilizados em cada execução.

### Analogia

Imagine uma catraca eletrônica.

O procedimento é sempre o mesmo:

```text
receber cartão → verificar → permitir ou negar acesso
```

O que muda são os cartões apresentados.

Nos testes parametrizados ocorre a mesma coisa:

```text
mesmo teste → diferentes dados → diferentes execuções
```

Você trabalhará com:

```java
@ParameterizedTest
@ValueSource
@CsvSource
@NullSource
@EmptySource
@NullAndEmptySource
@MethodSource
```

---

# 🟢 DESAFIO 1 — Validador de números

## Nível: Básico

Crie:

```java
public class ValidadorNumero {

    public static boolean ehPar(int numero) {
        return numero % 2 == 0;
    }
}
```

Crie a classe:

```text
ValidadorNumeroTest
```

### Teste 1.1 — Números pares

Utilize:

```java
@ParameterizedTest
@ValueSource
```

Teste:

```text
2
4
10
20
100
1000
```

Todos devem retornar:

```java
true
```

### Teste 1.2 — Números ímpares

Teste:

```text
1
3
7
15
99
101
```

Todos devem retornar:

```java
false
```

### Pense antes de continuar

Por que não seria interessante criar 12 métodos diferentes com `@Test`?

---

# 🟢 DESAFIO 2 — Validador de nomes de usuário

Crie:

```java
public class ValidadorUsuario {

    public static boolean nomeValido(String nome) {

        if (nome == null || nome.isBlank()) {
            return false;
        }

        return nome.length() >= 3;
    }
}
```

## Teste 2.1 — Nomes válidos

Utilize `@ValueSource` para testar:

```text
Ana
Carlos
Maria
Joao123
usuario
```

Todos devem retornar `true`.

---

## Teste 2.2 — Valores ausentes

Utilize:

```java
@ParameterizedTest
@NullAndEmptySource
```

para verificar:

```text
null
""
```

Ambos devem retornar:

```java
false
```

---

## Teste 2.3 — E o espaço?

Teste também:

```text
" "
"  "
```

### Questão

`@NullAndEmptySource` testa automaticamente Strings contendo espaços?

Descubra como adicionar esses valores ao teste.

---

# 🟡 DESAFIO 3 — Conversor de temperatura

## Nível: Intermediário

Agora precisamos relacionar **entrada e resultado esperado**.

Crie:

```java
public class ConversorTemperatura {

    public static double celsiusParaFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }
}
```

Crie um teste utilizando:

```java
@ParameterizedTest
@CsvSource
```

Teste:

| Celsius | Fahrenheit esperado |
| ------: | ------------------: |
|       0 |                  32 |
|      10 |                  50 |
|      20 |                  68 |
|      30 |                  86 |
|     100 |                 212 |
|     -10 |                  14 |
|     -40 |                 -40 |

Utilize:

```java
assertEquals(esperado, resultado, 0.001);
```

### Pergunta

Por que `@CsvSource` é mais adequado que `@ValueSource` neste caso?

---

# 🟡 DESAFIO 4 — Classificador de idade

Uma aplicação precisa classificar usuários de acordo com sua idade.

Crie:

```java
public class ClassificadorIdade {

    public static String classificar(int idade) {

        if (idade < 0) {
            throw new IllegalArgumentException(
                "Idade não pode ser negativa."
            );
        }

        if (idade <= 12)
            return "CRIANCA";

        if (idade <= 17)
            return "ADOLESCENTE";

        if (idade <= 59)
            return "ADULTO";

        return "IDOSO";
    }
}
```

## Teste 4.1 — Classificações

Utilize `@CsvSource`:

| Idade | Esperado    |
| ----: | ----------- |
|     0 | CRIANCA     |
|     5 | CRIANCA     |
|    12 | CRIANCA     |
|    13 | ADOLESCENTE |
|    17 | ADOLESCENTE |
|    18 | ADULTO      |
|    30 | ADULTO      |
|    59 | ADULTO      |
|    60 | IDOSO       |
|    80 | IDOSO       |

### 🔎 Observe

Alguns valores foram escolhidos propositalmente:

```text
12 → 13
17 → 18
59 → 60
```

Eles representam **fronteiras das regras de negócio**.

---

## Teste 4.2 — Idades inválidas

Utilize:

```java
@ParameterizedTest
@ValueSource
assertThrows()
```

Teste:

```text
-1
-5
-20
-100
```

Todos devem lançar:

```java
IllegalArgumentException
```

Além do tipo da exceção, verifique a mensagem:

```text
Idade não pode ser negativa.
```

---

# 🟡 DESAFIO 5 — Controle de acesso a evento

Um evento utiliza pulseiras identificadas por códigos.

Crie:

```java
public class ControleAcesso {

    public static boolean podeEntrar(
            int idade,
            boolean acompanhado) {

        if (idade < 0) {
            throw new IllegalArgumentException(
                "Idade inválida."
            );
        }

        if (idade >= 18) {
            return true;
        }

        return idade >= 16 && acompanhado;
    }
}
```

## Regra

* 18 anos ou mais → entrada permitida;
* 16 ou 17 anos acompanhado → entrada permitida;
* 16 ou 17 anos desacompanhado → entrada negada;
* menor de 16 anos → entrada negada.

Crie um teste parametrizado com `@CsvSource`.

Você deverá determinar o resultado esperado:

| Idade | Acompanhado | Resultado esperado |
| ----: | :---------: | :----------------: |
|    15 |     true    |          ?         |
|    15 |    false    |          ?         |
|    16 |     true    |          ?         |
|    16 |    false    |          ?         |
|    17 |     true    |          ?         |
|    17 |    false    |          ?         |
|    18 |     true    |          ?         |
|    18 |    false    |          ?         |
|    25 |    false    |          ?         |

### Importante

Não copie simplesmente a tabela para o teste.

Primeiro analise a **regra de negócio** e complete a última coluna.

---

# 🟠 DESAFIO 6 — Taxa de estacionamento

## Nível: Intermediário/Avançado

Um estacionamento possui a seguinte regra:

| Tempo estacionado     |    Valor |
| --------------------- | -------: |
| até 1 hora            |  R$ 5,00 |
| mais de 1 até 3 horas | R$ 10,00 |
| mais de 3 até 6 horas | R$ 15,00 |
| acima de 6 horas      | R$ 25,00 |

Crie:

```java
public class Estacionamento {

    public static double calcularValor(int horas) {

        if (horas <= 0) {
            throw new IllegalArgumentException(
                "Tempo inválido."
            );
        }

        if (horas <= 1)
            return 5.0;

        if (horas <= 3)
            return 10.0;

        if (horas <= 6)
            return 15.0;

        return 25.0;
    }
}
```

## Teste 6.1 — Valores cobrados

Crie um `@ParameterizedTest` com `@CsvSource`.

Você deverá escolher pelo menos **10 valores diferentes**.

### Obrigatório

Seus dados precisam testar as fronteiras:

```text
1 → 2 horas
3 → 4 horas
6 → 7 horas
```

Não fornecemos a tabela pronta desta vez.

**Você deverá decidir quais casos de teste são importantes.**

---

## Teste 6.2 — Valores inválidos

Utilize `@ValueSource` para testar:

```text
0
-1
-5
-100
```

Verifique:

```java
IllegalArgumentException
```

e a mensagem:

```text
Tempo inválido.
```

---

# 🟠 DESAFIO 7 — Validador de senha

Agora os dados de teste começam a ficar mais complexos.

Crie:

```java
public class ValidadorSenha {

    public static boolean senhaValida(String senha) {

        if (senha == null) {
            return false;
        }

        return senha.length() >= 8
                && senha.matches(".*[A-Z].*")
                && senha.matches(".*[0-9].*");
    }
}
```

Uma senha válida deve possuir:

* pelo menos 8 caracteres;
* pelo menos uma letra maiúscula;
* pelo menos um número.

Utilize:

```java
@ParameterizedTest
@CsvSource
```

Teste pelo menos:

```text
Senha123   → true
Teste2026  → true
senha123   → false
SENHAAAA   → false
Abc1       → false
12345678   → false
```

Depois acrescente **três casos criados por você**.

---

# 🔴 DESAFIO 8 — Sistema de login com @MethodSource

## Nível: Avançado

Um sistema precisa verificar tentativas de login.

Crie:

```java
public class Autenticador {

    public static boolean autenticar(
            String usuario,
            String senha,
            boolean ativo) {

        if (usuario == null || senha == null) {
            return false;
        }

        return usuario.equals("admin")
                && senha.equals("Senai123")
                && ativo;
    }
}
```

Agora utilize:

```java
@ParameterizedTest
@MethodSource
```

O método fornecedor deverá gerar diferentes combinações utilizando:

```java
Stream<Arguments>
Arguments.of()
```

Teste situações como:

```text
usuário correto + senha correta + ativo
usuário correto + senha errada + ativo
usuário errado + senha correta + ativo
usuário correto + senha correta + inativo
usuário null
senha null
```

Você deverá determinar o resultado esperado para cada situação.

---

# 🔴 DESAFIO 9 — Sistema de notas

Agora você receberá principalmente a **regra de negócio**.

Implemente:

```java
public class ClassificadorNota {

    public static String classificar(double nota) {
        // desenvolver
    }
}
```

## Regras

| Nota         | Situação    |
| ------------ | ----------- |
| 0 até 4.9    | REPROVADO   |
| 5.0 até 6.9  | RECUPERACAO |
| 7.0 até 10.0 | APROVADO    |

Valores:

```text
nota < 0
nota > 10
```

devem lançar:

```java
IllegalArgumentException
```

Mensagem:

```text
Nota deve estar entre 0 e 10.
```

## Você deverá desenvolver vários testes

Seus testes precisam verificar:

1. alunos reprovados;
2. alunos em recuperação;
3. alunos aprovados;
4. limites das classificações;
5. notas negativas;
6. notas maiores que 10;
7. tipo da exceção;
8. mensagem da exceção.

Utilize obrigatoriamente:

```java
@ParameterizedTest
@ValueSource
@CsvSource
assertEquals()
assertThrows()
```

---

# 🏆 DESAFIO FINAL — Sistema de entrega de prova

Uma plataforma permite que estudantes entreguem atividades até determinado horário.

Crie:

```java
public class EntregaAtividade {

    public static String verificar(
            int minutosAtraso,
            boolean justificativa) {

        // desenvolver
    }
}
```

## Regras

```text
minutosAtraso < 0
        → "NO_PRAZO"

minutosAtraso == 0
        → "NO_PRAZO"

1 até 10 minutos
        → "ATRASO_TOLERADO"

mais de 10 minutos + justificativa
        → "ANALISE_PROFESSOR"

mais de 10 minutos sem justificativa
        → "ATRASADA"
```

### Sua missão

Desenvolva a classe e crie testes suficientes para demonstrar que **todas as regras funcionam**.

Obrigatoriamente utilize testes de fronteira envolvendo:

```text
-1
0
1
10
11
```

Crie também outros casos que considerar importantes.

Você deverá decidir:

**`@ValueSource`, `@CsvSource` ou `@MethodSource`?**

Justifique sua escolha.

---

# 📦 Entrega

Organize o projeto:

```text
src
├── main/java
│   ├── ValidadorNumero.java
│   ├── ValidadorUsuario.java
│   ├── ConversorTemperatura.java
│   ├── ClassificadorIdade.java
│   ├── ControleAcesso.java
│   ├── Estacionamento.java
│   ├── ValidadorSenha.java
│   ├── Autenticador.java
│   ├── ClassificadorNota.java
│   └── EntregaAtividade.java
│
└── test/java
    ├── ValidadorNumeroTest.java
    ├── ValidadorUsuarioTest.java
    ├── ConversorTemperaturaTest.java
    ├── ClassificadorIdadeTest.java
    ├── ControleAcessoTest.java
    ├── EstacionamentoTest.java
    ├── ValidadorSenhaTest.java
    ├── AutenticadorTest.java
    ├── ClassificadorNotaTest.java
    └── EntregaAtividadeTest.java
```

# 📊 Critérios de avaliação

| Critério                                    |   Pontos |
| ------------------------------------------- | -------: |
| `@ParameterizedTest` utilizado corretamente |      1,5 |
| `@ValueSource`                              |      1,0 |
| `@CsvSource`                                |      1,5 |
| `@NullAndEmptySource`                       |      0,5 |
| `@MethodSource`                             |      1,5 |
| Testes de fronteira                         |      1,0 |
| Testes de exceção                           |      1,0 |
| Qualidade e variedade dos casos de teste    |      1,0 |
| Organização e nomes dos testes              |      0,5 |
| Todos os testes executando corretamente     |      0,5 |
| **TOTAL**                                   | **10,0** |

# 🧠 Reflexão final

Você possui três situações:

**A)** testar 20 números diferentes no mesmo método;

**B)** testar combinações de `idade + acompanhado + resultadoEsperado`;

**C)** testar objetos ou combinações mais complexas produzidas por um método Java.

Qual fonte você escolheria para cada uma?

```java
@ValueSource
@CsvSource
@MethodSource
```

Explique **por que escolheu cada uma**.

Nesta versão, os exercícios de **frete e desconto foram completamente retirados**. Também deixei os primeiros desafios mais guiados e, a partir do estacionamento, comecei a retirar parte dos casos prontos. Assim, no final, eles precisam **pensar nos casos de teste**, e não apenas transformar uma tabela em `@CsvSource`.
