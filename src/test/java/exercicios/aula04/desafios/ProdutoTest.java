package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    void calcularValorEmEstoqueDeveMultiplicarPrecoPelaQuantidade() {
        // Arrange
        Produto produto = new Produto("Bola",10.0,10);

        // Act
        double obtido = produto.calcularValorEmEstoque();

        // Assert
        assertEquals(100.0,obtido, 0.001);
    }

    @Test
    void verificarSeRetornaVerdadeiroQuandoHouverProdutosNoEstoque(){
        Produto produto = new Produto("Bola",10.0,10);

        assertTrue(produto.temEstoque());
    }

    @Test
    void verificarSeRetornaFalsoQuandoEstoqueEstaZerado(){
        Produto produto = new Produto("Bola",10.0,0);

        assertFalse(produto.temEstoque());
    }

    @Test
    void verificarSeRejeitaQuandoPrecoForZero(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 0, 10)
        );
    }

    @Test
    void verificarSeRejeitaQuandoPrecoForNegativo(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", -10, 10)
        );
    }

    @Test
    void verificarRejeicaoQuantidadeInicialNegativa(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 10.0, -1)
        );
    }

    @Test
    void verificarMensagensExcecoes(){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 0, 10)
        );

        assertEquals("O preço deve ser maior que zero.",excecao.getMessage());

        excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", -10, 10)
        );

        assertEquals("O preço deve ser maior que zero.",excecao.getMessage());

        excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 10.0, -1)
        );

        assertEquals("O estoque não pode ser negativo.",excecao.getMessage());
    }
}
