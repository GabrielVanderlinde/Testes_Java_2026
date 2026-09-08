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
        // Arrange: criar produto com estoque
        Produto produto = new Produto("Bola",10.0,10);

        // Act: verificar se tem estoque
        // Assert: deve retornar verdadeiro
        assertTrue(produto.temEstoque());
    }

    @Test
    void verificarSeRetornaFalsoQuandoEstoqueEstaZerado(){
        // Arrange: criar produto sem estoque
        Produto produto = new Produto("Bola",10.0,0);

        // Act: verificar se tem estoque
        // Assert: deve retornar falso
        assertFalse(produto.temEstoque());
    }

    @Test
    void verificarSeRejeitaQuandoPrecoForZero(){
        // Arrange & Assert: tentar criar produto com preço zero
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 0, 10)
        );
        assertEquals("O preço deve ser maior que zero.", excecao.getMessage());
    }

    @Test
    void verificarSeRejeitaQuandoPrecoForNegativo(){
        // Arrange & Assert: tentar criar produto com preço negativo
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", -10, 10)
        );
        assertEquals("O preço deve ser maior que zero.", excecao.getMessage());
    }

    @Test
    void verificarRejeicaoQuantidadeInicialNegativa(){
        // Arrange & Assert: tentar criar produto com quantidade negativa
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new Produto("Bola", 10.0, -1)
        );
        assertEquals("O estoque não pode ser negativo.", excecao.getMessage());
    }
}
