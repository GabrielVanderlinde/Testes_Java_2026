# 📝 Centro de Exercícios

Bem-vindo ao centro de exercícios do curso de Teste de Sistemas! Aqui você encontrará todos os desafios, práticas e gabaritos organizados por aula.

## 🎯 Como usar

Cada aula possui uma pasta dedicada contendo:

- **`desafio.html`** - Exercício autônomo para praticar os conceitos da aula
- **`feedback.html`** - Gabarito detalhado com explicações e soluções comentadas

## 💻 Exercícios Java Implementados

Além dos exercícios HTML, seu projeto inclui exercícios Java completos com testes JUnit 5 implementados nas aulas 1, 3, 4 e 5:

### Estrutura de Código Java

```
src/
├── main/
│   └── java/
│       └── exercicios/
│           ├── aula01/          # Classes: ContaBancaria, Livro
│           ├── aula03/          # Classes: Cliente, Pedido
│           ├── aula04/          # Classes: ContaBancaria, ReservaHotel, Retangulo, Triangulo
│           │   └── desafios/    # Classes: ContaDigital, Lampada, Produto, ReservaHotel, Usuario
│           └── aula05/          # Classes: CalculadoraFrete, Desconto
│               └── desafios/    # Classes: CalculadoraEstacionamento, CalculadoraFrete, CalculadoraPedido, Desconto, Item
└── test/
    └── java/
        └── exercicios/
            ├── aula01/          # Testes: LivroTest
            ├── aula03/          # Testes: ClienteTest, PedidoTest
            ├── aula04/          # Testes: ContaBancariaTest, ReservaHotelTest, RetanguloTest, TrianguloTest
            │   └── desafios/    # Testes: ContaDigitalTest, LampadaTest, ProdutoTest, ReservaHotelTest, UsuarioTest
            └── aula05/          # Testes: CalculadoraFreteTest, DescontoTest
                └── desafios/    # Testes: CalculadoraEstacionamentoTest, CalculadoraFreteTest, CalculadoraPedidoTest, DescontoTest
```

### Como Executar os Testes

Para compilar e executar os testes JUnit 5:

```bash
# Compilar o projeto
mvn compile

# Executar todos os testes
mvn test

# Executar testes de uma aula específica
mvn test -Dtest=exercicios.aula01.*
mvn test -Dtest=exercicios.aula04.desafios.*
```

### Exercícios por Aula

#### Aula 1 - Fundamentos de Teste + Revisão de POO
- **ContaBancaria.java** - Classe bancária com depósito, saque e validações
- **Livro.java** - Classe de livro com gestão de estoque
- **LivroTest.java** - Testes unitários completos

#### Aula 3 - Primeiros Testes com JUnit 5
- **Cliente.java** - Classe de cliente com ativação/desativação
- **Pedido.java** - Classe de pedido com soma de itens
- **ClienteTest.java** - Testes de ciclo de vida e assertions
- **PedidoTest.java** - Testes básicos de soma

#### Aula 4 - Asserções
- **Exercícios principais:** ContaBancaria, ReservaHotel, Retangulo, Triangulo
- **Desafios avançados:** ContaDigital, Lampada, Produto, ReservaHotel, Usuario
- **Testes completos** usando assertAll, assertThrows, assertEquals

#### Aula 5 - Testes Parametrizados
- **CalculadoraFrete.java** - Cálculo de frete parametrizado
- **Desconto.java** - Cálculo de desconto com timeout
- **Desafios:** CalculadoraEstacionamento, CalculadoraPedido, Item
- **Testes** usando @ParameterizedTest, @ValueSource, @CsvSource

## 📚 Estrutura

```
exercicios/
├── index.html              # Índice visual de todos os exercícios
├── README.md              # Este arquivo
├── aula-01/              # Fundamentos de Teste + Revisão de POO
│   ├── desafio.html
│   └── feedback.html
├── aula-02/              # Ambiente de Desenvolvimento
│   ├── desafio.html
│   └── feedback.html
├── aula-03/              # Primeiros Testes com JUnit 5
│   ├── desafio.html
│   └── feedback.html
├── aula-04/              # Asserções
│   ├── desafio.html
│   └── feedback.html
├── aula-05/              # Testes Parametrizados
│   ├── desafio.html
│   └── feedback.html
├── aula-06/              # Organização de Testes
│   ├── desafio.html
│   └── feedback.html
├── aula-07/              # Test Doubles
│   ├── desafio.html
│   └── feedback.html
├── aula-08/              # Mockito I ⚠️ Avaliação
│   ├── desafio.html
│   └── feedback.html
├── aula-09/              # Mockito II
│   ├── desafio.html
│   └── feedback.html
├── aula-10/              # Cobertura de Código
│   ├── desafio.html
│   └── feedback.html
├── aula-11/              # TDD
│   ├── desafio.html
│   └── feedback.html
├── aula-12/              # Spring + Testes de Serviço
│   ├── desafio.html
│   └── feedback.html
├── aula-13/              # Testes de Repositório 🚩 Projeto Integrador
│   ├── desafio.html
│   └── feedback.html
├── aula-14/              # Testes de Controller 🚩 Projeto Integrador
│   ├── desafio.html
│   └── feedback.html
└── aula-15/              # Projeto Integrador — Fechamento 🚩 Entrega Final
    ├── desafio.html
    └── feedback.html
```

## 🏷️ Legendas

- ⚠️ **Avaliação** - Aula com avaliação intermediária (Aula 8)
- 🚩 **Projeto Integrador** - Aulas que compõem o projeto integrador final (Aulas 13-15)

## 💡 Dicas de Estudo

1. **Tente primeiro sem olhar o gabarito** - Os desafios são projetados para serem resolvidos autonomamente
2. **Use o timer** - Cada desafio tem um cronômetro sugerido para simular condições reais
3. **Compare com o feedback** - Depois de resolver, confira o feedback para aprender diferentes abordagens
4. **Revise os conceitos** - Se tiver dificuldade, volte ao material da aula correspondente em `aulas/`

## 🔗 Links Relacionados

- [Plano de Ensino Completo](../docs/README.md)
- [Índice Visual de Exercícios](index.html)
- [Material das Aulas](../aulas/)

---

**Professora:** Karize Viecelli — @karizeviecelli  
**Curso:** Técnico em Desenvolvimento de Sistemas  
**Unidade Curricular:** Teste de Sistemas