package exercicios.aula05.desafios;

import exercicios.aula05.Desconto;
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

        double obtido = exercicios.aula05.Desconto.calcular(preco, percentual);

        assertEquals(esperado, obtido, 0.001);
    }

    @ParameterizedTest(name = "preço {0} deve ser rejeitado")
    @ValueSource(doubles = {-0.01, -1.0, -100.0})
    void precoNegativoDeveLancarExcecao(double preco) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> exercicios.aula05.Desconto.calcular(preco, 10)
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
                () -> exercicios.aula05.Desconto.calcular(100.0, percentual)
        );

        assertEquals(
                "O percentual deve estar entre 0 e 100.",
                excecao.getMessage()
        );
    }

    @Test
    @Timeout(value = 20, unit = TimeUnit.MILLISECONDS)
    void calcularDeveTerminarRapidamente() {
        Desconto.calcular(250.0, 15);
    }
}
