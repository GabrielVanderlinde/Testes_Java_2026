package exercicios.aula05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DescontoTest {

    @ParameterizedTest(name = "caso {index}: R$ {0} - {1}% = R$ {2}")
    @CsvSource({
            "100.00,  10,  90.00",
            "200.00,  25, 150.00",
            " 80.00,   0,  80.00",
            " 50.00, 100,   0.00"
    })
    void calcularDeveAplicarPercentual(
            double preco,
            int percentual,
            double esperado) {

        double obtido = Desconto.calcular(preco, percentual);

        assertEquals(esperado, obtido, 0.001);
    }

    @ParameterizedTest(name = "preço {0} deve ser rejeitado")
    @ValueSource(doubles = {-0.01, -1.0, -100.0})
    void precoNegativoDeveLancarExcecao(double preco) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Desconto.calcular(preco, 10)
        );

        assertEquals(
                "O preço não pode ser negativo.",
                excecao.getMessage()
        );
    }

    @ParameterizedTest(name = "percentual inválido: {0}")
    @ValueSource(ints = {-1, 101})
    void percentualForaDoIntervaloDeveFalhar(int percentual) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Desconto.calcular(100.0, percentual)
        );

        assertEquals(
                "O percentual deve estar entre 0 e 100.",
                excecao.getMessage()
        );
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
    void calcularDeveTerminarRapidamente() {
        // Arrange: não há setup necessário
        // Act: calcular desconto
        // Assert: o teste passa se terminar antes do limite de 10ms
        Desconto.calcular(250.0, 15);
    }

    @ParameterizedTest(name = "fronteira válida: {0}%")
    @ValueSource(ints = {0, 100})
    void fronteirasValidasDevemSerAceitas(int percentual) {
        // Arrange: preço válido
        double preco = 100.0;

        // Act: calcular desconto com fronteiras válidas
        double resultado = Desconto.calcular(preco, percentual);

        // Assert: verificar cálculo correto
        double esperado = (percentual == 0) ? 100.0 : 0.0;
        assertEquals(esperado, resultado, 0.001);
    }

    @ParameterizedTest(name = "vizinho interno da fronteira {0}: {1}%")
    @CsvSource({
            "0, 1",
            "100, 99"
    })
    void vizinhosInternosDasFronteirasDevemFuncionar(int fronteira, int vizinho) {
        // Arrange: preço válido
        double preco = 100.0;

        // Act: calcular desconto com vizinho interno
        double resultado = Desconto.calcular(preco, vizinho);

        // Assert: verificar cálculo correto
        double esperado = preco * (100 - vizinho) / 100.0;
        assertEquals(esperado, resultado, 0.001);
    }

    @ParameterizedTest(name = "imediatamente fora da fronteira: {0}%")
    @ValueSource(ints = {-1, 101})
    void percentuaisImediatamenteForaDasFronteirasDevemFalhar(int percentual) {
        // Arrange: preço válido
        double preco = 100.0;

        // Act & Assert: tentar calcular com percentual fora das fronteiras
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Desconto.calcular(preco, percentual)
        );

        assertEquals("O percentual deve estar entre 0 e 100.", excecao.getMessage());
    }
}
