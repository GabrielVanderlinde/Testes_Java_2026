# AULA 01 — Estratégias de Testes: Introdução ao TDD

**SERVIÇO NACIONAL DE APRENDIZAGEM INDUSTRIAL – SENAI/SC**

Curso Técnico em Desenvolvimento de Sistemas — Unidade Curricular: Testes de Sistemas
Professora Karize Viecelli @karizeviecelli — 04/08/2026

---

## 1. Plano de Aula (MSEP)

| Aula/Data | Capacidade | Conhecimentos Relacionados | Estratégias de Ensino e Descrição da Atividade | Recursos e Ambientes | Instrumentos de Avaliação |
|---|---|---|---|---|---|
| 01 - 04/08/2026 | Identificar tipos, funções e estratégias de teste; reconhecer métodos e técnicas de testes para correção de falhas; empregar o TDD no desenvolvimento de funcionalidades. | Estratégias de teste; Test-Driven Development (TDD); Ciclo Red-Green-Refactor; Qualidade e melhoria contínua. | Aula expositiva dialogada sobre a história e o ciclo TDD; atividade prática em duplas: primeiro ciclo TDD (Red-Green-Refactor) com JUnit; desafio de impacto social (acessibilidade). | Laboratório de Informática; Projetor; IDE (VS Code ou Eclipse); Java + JUnit 5; repositório Git. | Ficha de observação de desempenho; verificação do código com testes no repositório. |

---

## 2. Recapitulação Ativa — Guia de Estudos

### 2.1 O que é TDD?

O **TDD (Test-Driven Development ou Desenvolvimento Orientado a Testes)** é uma estratégia de desenvolvimento em que os testes são escritos **ANTES** do código de produção. Em vez de testar depois de implementar, o teste define o comportamento esperado primeiro, e o código nasce para satisfazer esse teste. O TDD não é meramente uma ferramenta de teste, mas uma prática de design e de disciplina de programação: ele orienta o desenvolvimento em pequenos passos, mantendo o código sempre verificável e funcional.

### 2.2 História e contexto (a "estória" do TDD)

O TDD foi sistematizado por **Kent Beck** no final da década de 1990, dentro do movimento *Extreme Programming (XP)*. Beck resgatou a ideia de "test-first" (testar primeiro), que já circulava na comunidade Smalltalk, e a transformou em uma prática central do XP, popularizada mundialmente no livro *"Test-Driven Development: By Example"*. No Brasil, a prática se difundiu junto com metodologias ágeis, tornando-se um dos pilares da qualidade de software em indústrias que adotam Scrum e DevOps. No TDD, o teste não é um "extra" — é a especificação executável do sistema.

### 2.3 O ciclo Red → Green → Refactor (o coração do TDD)

O ciclo fundamental do TDD consiste em três fases iterativas:

- **RED (Vermelho):** Escreva um teste que falhe. O teste define o comportamento desejado antes do código existir. A falha confirma que o requisito ainda não foi implementado.
- **GREEN (Verde):** Escreva o **MÍNIMO** de código necessário para fazer o teste passar. O foco é a funcionalidade, não a perfeição estética.
- **REFACTOR (Refatorar):** Com a segurança dos testes passando, melhore o código (remova duplicidade, melhore nomes). A cada alteração, os testes devem ser executados para garantir que nada quebrou.

**Regra de ouro:** nenhum código de produção é escrito sem antes existir um teste falhando.

### 2.4 Analogia didática

Pense no TDD como dirigir seguindo um **GPS**: antes de sair, você informa o destino (o teste define o ponto de chegada). O GPS mostra o caminho errado inicialmente se você não se moveu (vermelho), você ajusta a rota (implementa o mínimo) e chega ao destino (verde), e depois pode refinar o percurso para ficar mais eficiente (refactor). É como cozinhar com uma receita: a receita (teste) descreve o resultado esperado antes mesmo de ligar o fogão.

### 2.5 Benefícios e desafios

- **Benefícios:** Código mais testável, design emergente, refatoração segura, documentação viva e feedback rápido.
- **Desafios:** Exige alta disciplina, curva de aprendizado inicial e percepção de lentidão no início do processo.

---

## 3. Atividades Práticas

### Atividade 1 — Analisando cenários (conceitual)

a) Um desenvolvedor implementou a função, rodou e só depois escreveu o teste. Ele está seguindo TDD? Justifique.

b) Ordene corretamente as etapas: refatorar / escrever teste que falha / implementar o mínimo para passar.

c) Em uma frase com analogia **AUTORAL**, explique por que o teste deve ser escrito antes do código.

d) Classifique cada fase do ciclo: qual fase permite código "feio" de propósito? Qual fase tem os testes como rede de segurança?

### Atividade 2 — Primeiro ciclo TDD na prática (mão na massa)

**Contexto:** A biblioteca da escola precisa de um módulo para calcular multa de atraso. Regra: `R$ 2,00` por dia de atraso; a multa não pode ser negativa.

**Passos:**

1. Criar o projeto Java com JUnit 5 e repositório Git.
2. **RED:** Escrever o teste para o método `calcularMulta(int diasAtraso)`.
3. **GREEN:** Implementar o código mínimo para o teste passar.
4. **REFACTOR:** Extrair a constante `VALOR_POR_DIA = 2.00`.
5. Realizar commits: `RED: teste falhando`, `GREEN: implementação mínima`, `REFACTOR: constante extraída`.

### Atividade 3 — Estória de usuário com ciclo completo

**Estória:** "Como usuário do sistema de empréstimos, quero validar meu CPF no cadastro para garantir que apenas dados corretos sejam aceitos."

**Critérios de aceite:**

- CPF com 11 dígitos.
- Dígitos verificadores corretos.
- CPFs com todos os dígitos iguais (ex.: 111.111.111-11) são inválidos.

**Tarefa:** aplicar o ciclo completo (Red-Green-Refactor) para os três critérios e versionar no Git.

### Atividade 4 — Desafio de impacto social e inclusão digital

Desenvolva com TDD uma função de acessibilidade para formatar texto para leitores de tela (*screen readers*), substituindo abreviações comuns por extenso: "vc" → "você", "pq" → "porque", "td" → "tudo".

**Reflexão:** Como essa funcionalidade contribui para a inclusão digital de pessoas com deficiência visual?

---

## 4. Gabarito Comentado (Exclusivo para o Professor)

### Atividade 1

- **a)** Não. No TDD o teste é *test-first* (escrito antes). Escrever depois caracteriza teste tradicional (*test-last*).
- **b)** 1. Escrever teste que falha; 2. Implementar o mínimo; 3. Refatorar.
- **c)** Resposta livre; espera-se analogia autoral (ex.: "a receita antes de cozinhar", "a planta antes de construir", "o GPS antes de dirigir"). Valorize a criatividade, sem exigir resposta única.
- **d)** A fase **GREEN** permite código "feio" de propósito (objetivo é passar no teste). A fase **REFACTOR** usa os testes como rede de segurança.

**Dica de condução:** use o quadro com as três cores (vermelho, verde, azul/refactor) enquanto os alunos respondem.

### Atividade 2 (exemplo de código)

~~~java
// RED - Teste
@Test
void deveCalcularMultaCorretamente() {
    CalculadoraMulta calc = new CalculadoraMulta();
    assertEquals(4.0, calc.calcularMulta(2));
    assertEquals(0.0, calc.calcularMulta(-1));
}

// GREEN - Implementação mínima
public class CalculadoraMulta {
    public double calcularMulta(int dias) {
        if (dias < 0) return 0.0;
        return dias * 2.0;
    }
}

// REFACTOR - Constante extraída
public class CalculadoraMulta {
    private static final double VALOR_POR_DIA = 2.00;

    public double calcularMulta(int dias) {
        if (dias < 0) return 0.0;
        return dias * VALOR_POR_DIA;
    }
}
~~~

**Verificação:** exigir commits separados por fase (`RED`, `GREEN`, `REFACTOR`). Circular pela sala observando se o teste foi escrito **antes** da implementação — este é o comportamento mais importante a avaliar nesta aula.

### Atividade 3

- **Testes (RED):** CPF válido (ex.: 529.982.247-25 — dígitos verificadores corretos); CPF inválido (dígito trocado); CPF `111.111.111-11` → inválido.
- **Implementação (GREEN):** algoritmo dos dígitos verificadores (módulo 11), rejeitando sequências repetidas.
- **Refactor:** extrair métodos privados (`calcularDigitoVerificador`, `validarTamanho`).

**Dica:** se a turma tiver dificuldade com o algoritmo, permita usar uma biblioteca validadora, mas cobrando que os testes sejam autorais e cubram os casos de borda.

### Atividade 4

- **Testes (RED):** mapear as abreviações ("vc" → "você", "pq" → "porque", "td" → "tudo"); texto sem abreviações permanece igual; maiúsculas preservadas.
- **Implementação (GREEN/REFACTOR):** substituição por dicionário (`Map<String, String>`), preservando o restante do texto.
- **Debate:** relacionar com acessibilidade web (**WCAG**) e como a síntese de voz falha ao ler abreviações, prejudicando a compreensão de usuários cegos. Conectar com o perfil do curso: pequenos gestos técnicos geram impacto social real.

---

## 5. Roda de Fechamento / Avaliação da Aula

Cada dupla deve apresentar um *commit* específico de uma das fases (RED, GREEN ou REFACTOR) e explicar em uma frase o que aprendeu. A avaliação considerará a disciplina em não pular a fase RED, o versionamento correto e a qualidade das reflexões.

---

## 6. Referências

- BECK, Kent. **Test-Driven Development: By Example**. Boston: Addison-Wesley, 2003.
- SENAI DN / SENAI SC. **Teste de sistema**. Brasília: SENAI DN, 2021.
- ROCHA, Anne Caroline. **Simplificando teste de software**. Santa Catarina: Clube de Autores, 2023.

---

*Professora Karize Viecelli @karizeviecelli — SENAI/SC — 2026*