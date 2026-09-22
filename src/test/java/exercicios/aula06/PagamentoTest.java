package exercicios.aula06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes de Pagamento")
class PagamentoTest {

    @Nested
    @DisplayName("Testes de PagamentoPix")
    class PixTests {

        @Test
        @DisplayName("Taxa deve ser zero")
        void taxaZero() {
            PagamentoPix pix = new PagamentoPix(100.0);
            assertEquals(0.0, pix.calcularTaxa(), 0.001);
        }

        @Test
        @DisplayName("Valor invalido deve lancar excecao")
        void valorInvalido() {
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(0.0)
            );
            assertEquals("O valor deve ser maior que zero.", excecao.getMessage());
        }
    }

    @Nested
    @Tag("cartao")
    @DisplayName("Testes de PagamentoCartao")
    class CartaoTests {

        @Test
        @DisplayName("Taxa deve ser 2,5%")
        void taxa2_5PorCento() {
            PagamentoCartao cartao = new PagamentoCartao(100.0);
            assertEquals(2.5, cartao.calcularTaxa(), 0.001);
        }

        @Test
        @DisplayName("Valor invalido deve lancar excecao")
        void valorInvalido() {
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoCartao(-10.0)
            );
            assertEquals("O valor deve ser maior que zero.", excecao.getMessage());
        }
    }

    @Nested
    @DisplayName("Testes Polimorficos")
    class PolimorfismoTests {

        @Test
        @DisplayName("Polimorfismo deve calcular taxas corretas")
        void polimorfismo() {
            Pagamento pix = new PagamentoPix(100.0);
            Pagamento cartao = new PagamentoCartao(100.0);

            assertAll(
                    "Taxas polimorficas",
                    () -> assertEquals(0.0, pix.calcularTaxa(), 0.001, "Pix deve ter taxa zero"),
                    () -> assertEquals(2.5, cartao.calcularTaxa(), 0.001, "Cartao deve ter taxa de 2,5%")
            );
        }
    }
}
