package exercicios.aula05.desafios;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPedidoTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("cenariosDePedido")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void calcularDeveAtenderCenariosValidos(
            String descricao,
            Item item,
            int percentualCupom,
            double esperado) {

        double obtido = CalculadoraPedido.calcular(item, percentualCupom);

        assertEquals(esperado, obtido, 0.001);
    }

    static Stream<Arguments> cenariosDePedido() {
        return Stream.of(
                Arguments.of("Fronteira mínima de desconto (0%)", new Item("Mouse", 50.0, 2), 0, 100.0),
                Arguments.of("Vizinho interno mínimo do desconto (1%)", new Item("Teclado", 100.0, 1), 1, 99.0),
                Arguments.of("Vizinho interno máximo do desconto (29%)", new Item("Monitor", 200.0, 2), 29, 284.0),
                Arguments.of("Fronteira máxima de desconto (30%)", new Item("Cadeira", 300.0, 1), 30, 210.0),
                Arguments.of("Fronteiras válidas de preço (0.01) e quantidade (1)", new Item("Parafuso", 0.01, 1), 0, 0.01)
        );
    }

    @ParameterizedTest(name = "Item nulo deve ser rejeitado")
    @NullSource
    void itemNuloDeveLancarExcecao(Item item) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraPedido.calcular(item, 10)
        );
        assertEquals("O item é obrigatório.", excecao.getMessage());
    }

    @ParameterizedTest(name = "Nome ausente deve lançar exceção: [{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t"})
    void nomeAusenteDeveLancarExcecao(String nome) {
        Item itemInvalido = new Item(nome, 50.0, 1);
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraPedido.calcular(itemInvalido, 10)
        );
        assertEquals("O nome do item é obrigatório.", excecao.getMessage());
    }

    @ParameterizedTest(name = "Cupom inválido: {0}%")
    @ValueSource(ints = {-1, 31})
    void cupomInvalidoDeveLancarExcecao(int percentualCupom) {
        Item itemValido = new Item("Produto", 100.0, 1);
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraPedido.calcular(itemValido, percentualCupom)
        );
        assertEquals("O cupom deve estar entre 0 e 30.", excecao.getMessage());
    }

    @ParameterizedTest(name = "Preço inválido: R$ {0}")
    @ValueSource(doubles = {0.0, -0.01, -100.0})
    void precoInvalidoDeveLancarExcecao(double preco) {
        Item itemInvalido = new Item("Cabo", preco, 1);
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraPedido.calcular(itemInvalido, 0)
        );
        assertEquals("O preço deve ser maior que zero.", excecao.getMessage());
    }

    @ParameterizedTest(name = "Quantidade inválida: {0}")
    @ValueSource(ints = {0, -1, -5})
    void quantidadeInvalidaDeveLancarExcecao(int quantidade) {
        Item itemInvalido = new Item("Cabo", 10.0, quantidade);
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraPedido.calcular(itemInvalido, 0)
        );
        assertEquals("A quantidade deve ser maior que zero.", excecao.getMessage());
    }
}
