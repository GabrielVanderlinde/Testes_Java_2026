# Aula 06 — Organização de Testes + Revisão de Herança e Polimorfismo

**Módulo:** 2 — JUnit 5 na Prática
**Carga horária:** 4 horas
**Professor(a):** [Nome/@handle da turma]

---

## 🎯 Objetivos da aula

- Usar `@DisplayName` para dar nomes legíveis aos testes na saída do JUnit;
- Organizar testes relacionados com `@Nested`;
- Categorizar testes com `@Tag`;
- Revisar herança e polimorfismo, dois pilares de POO essenciais para testar hierarquias de classes;
- Escrever testes para uma pequena hierarquia de classes.

---

## 🖼️ Retomando a analogia — organizando o arquivo do tribunal

Um tribunal com centenas de audiências precisa de um **sistema de arquivamento**: processos agrupados por tema, com nomes claros na capa de cada pasta. `@DisplayName`, `@Nested` e `@Tag` são exatamente isso para nossas classes de teste. E, já que vamos organizar audiências por "família de casos", é um ótimo momento para revisar como **famílias de réus** funcionam em POO — ou seja, herança e polimorfismo.

---

## 📚 Conteúdo teórico

### 1. `@DisplayName` — nomes legíveis

```java
@Test
@DisplayName("Deve lançar exceção ao sacar valor maior que o saldo")
void sacarValorMaiorQueSaldoDeveLancarExcecao() {
    // ...
}
```

Na saída de execução, o IntelliJ mostra a frase do `@DisplayName` em vez do nome técnico do método — muito mais legível em relatórios e apresentações.

### 2. `@Nested` — agrupando testes relacionados

```java
class ContaBancariaTest {

    @Nested
    @DisplayName("Testes de depósito")
    class TestesDeposito {
        @Test
        void depositoComValorPositivoDeveFuncionar() { ... }

        @Test
        void depositoComValorNegativoDeveLancarExcecao() { ... }
    }

    @Nested
    @DisplayName("Testes de saque")
    class TestesSaque {
        @Test
        void saqueComSaldoSuficienteDeveFuncionar() { ... }

        @Test
        void saqueComSaldoInsuficienteDeveLancarExcecao() { ... }
    }
}
```

`@Nested` cria classes internas de teste, agrupando cenários relacionados — muito útil quando uma classe tem várias regras de negócio distintas.

### 3. `@Tag` — categorizando testes

```java
@Test
@Tag("rapido")
void testeSimples() { ... }

@Test
@Tag("lento")
void testeComBancoDeDados() { ... }
```

Tags permitem, por exemplo, rodar apenas os testes rápidos durante o desenvolvimento do dia a dia, e reservar os testes lentos para a pipeline de integração contínua.

### 4. Revisão de POO: herança e polimorfismo

**Herança** permite que uma classe (subclasse) reaproveite atributos e métodos de outra (superclasse):

```java
// Superclasse: define o que é comum a todo Funcionario
public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    // Método abstrato: cada tipo de funcionário calcula o salário à sua maneira
    public abstract double calcularSalario();

    public String getNome() { return nome; }
}

// Subclasse: Vendedor ganha salário base + comissão
public class Vendedor extends Funcionario {
    private double totalVendas;

    public Vendedor(String nome, double salarioBase, double totalVendas) {
        super(nome, salarioBase); // reaproveita o construtor da superclasse
        this.totalVendas = totalVendas;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (totalVendas * 0.05); // 5% de comissão
    }
}

// Subclasse: Gerente ganha salário base + bônus fixo
public class Gerente extends Funcionario {
    public Gerente(String nome, double salarioBase) {
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return salarioBase + 1000.0; // bônus fixo de gestão
    }
}
```

**Polimorfismo** é a capacidade de tratar objetos de subclasses diferentes de forma uniforme, através do tipo da superclasse — e cada um se comporta "à sua maneira":

```java
Funcionario funcionario1 = new Vendedor("Ana", 2000.0, 10000.0);
Funcionario funcionario2 = new Gerente("Carlos", 4000.0);

// Mesmo "comando", comportamentos diferentes — isso é polimorfismo
funcionario1.calcularSalario(); // usa a lógica de Vendedor
funcionario2.calcularSalario(); // usa a lógica de Gerente
```

### 5. Por que isso importa para testes?

Cada subclasse deve ter sua **própria classe de teste**, verificando sua regra específica de `calcularSalario()`. E podemos usar `@Nested` para organizar os testes de uma hierarquia inteira dentro de uma única classe, se fizer sentido no seu projeto.

```java
class FuncionarioTest {

    @Nested
    @DisplayName("Testes de Vendedor")
    class TestesVendedor {
        @Test
        @DisplayName("Salário deve ser base + 5% das vendas")
        void calcularSalarioDeveIncluirComissao() {
            Vendedor vendedor = new Vendedor("Ana", 2000.0, 10000.0);
            assertEquals(2500.0, vendedor.calcularSalario());
        }
    }

    @Nested
    @DisplayName("Testes de Gerente")
    class TestesGerente {
        @Test
        @DisplayName("Salário deve ser base + bônus fixo de 1000")
        void calcularSalarioDeveIncluirBonus() {
            Gerente gerente = new Gerente("Carlos", 4000.0);
            assertEquals(5000.0, gerente.calcularSalario());
        }
    }
}
```

---

<a id="atividade"></a>
## 💻 Atividade Prática

**Duração sugerida:** 80 minutos

### Passo a passo

1. Crie a superclasse abstrata `FormaGeometrica`, com o método abstrato `calcularArea()`;
2. Crie as subclasses `Quadrado` (recebe `lado`) e `Circulo` (recebe `raio`), cada uma implementando `calcularArea()` corretamente;
3. Crie a classe de teste `FormaGeometricaTest`, usando `@Nested` para separar os testes de `Quadrado` e de `Circulo`;
4. Use `@DisplayName` em todos os métodos de teste, com frases claras;
5. Marque os testes de `Circulo` com `@Tag("geometria-circular")`.

[Ver Gabarito »](#gabarito)

---

<a id="gabarito"></a>
## ✅ Gabarito

```java
package br.edu.testesistemas.formas;

public abstract class FormaGeometrica {
    public abstract double calcularArea();
}
```

```java
package br.edu.testesistemas.formas;

public class Quadrado extends FormaGeometrica {
    private double lado;

    public Quadrado(double lado) {
        if (lado <= 0) throw new IllegalArgumentException("Lado deve ser positivo.");
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}
```

```java
package br.edu.testesistemas.formas;

public class Circulo extends FormaGeometrica {
    private double raio;

    public Circulo(double raio) {
        if (raio <= 0) throw new IllegalArgumentException("Raio deve ser positivo.");
        this.raio = raio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
}
```

```java
package br.edu.testesistemas.formas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormaGeometricaTest {

    @Nested
    @DisplayName("Testes de Quadrado")
    class TestesQuadrado {

        @Test
        @DisplayName("Área do quadrado deve ser lado ao quadrado")
        void calcularAreaDeveElevarLadoAoQuadrado() {
            Quadrado quadrado = new Quadrado(4.0);
            assertEquals(16.0, quadrado.calcularArea());
        }
    }

    @Nested
    @DisplayName("Testes de Círculo")
    @Tag("geometria-circular")
    class TestesCirculo {

        @Test
        @DisplayName("Área do círculo deve seguir a fórmula π × r²")
        void calcularAreaDeveUsarFormulaCorreta() {
            Circulo circulo = new Circulo(2.0);
            assertEquals(Math.PI * 4, circulo.calcularArea(), 0.0001);
            // O terceiro parâmetro (0.0001) é a "margem de erro" aceitável,
            // necessária ao comparar números double com casas decimais
        }
    }
}
```

---

**Próxima aula:** vamos conhecer os **test doubles** — dummy, stub, fake, spy e mock — os "atores contratados" que vão nos ajudar a testar código que depende de outras partes do sistema.
