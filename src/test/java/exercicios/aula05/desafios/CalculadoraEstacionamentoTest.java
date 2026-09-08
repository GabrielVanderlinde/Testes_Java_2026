package exercicios.aula05.desafios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraEstacionamentoTest {

    @ParameterizedTest(name = "horas={0}, fimDeSemana={1}, esperado={2}")
    @CsvSource({
            " 1, false,  5.0",
            " 1, true,   6.0",
            " 2, false,  8.0",
            " 2, true,   9.6",
            "23, false, 71.0",
            "23, true,  85.2",
            "24, false, 74.0",
            "24, true,  88.8"
    })
    void calcularDeveRetornarValorCorreto(int horas, boolean fimDeSemana, double esperado) {
        double obtido = CalculadoraEstacionamento.calcular(horas, fimDeSemana);

        assertEquals(esperado, obtido, 0.001);
    }

    @ParameterizedTest(name = "permanência inválida: {0}")
    @ValueSource(ints = {0, -1, 25})
    void permanenciaInvalidaDeveLancarExcecao(int horas) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraEstacionamento.calcular(horas, false)
        );

        assertEquals("A permanência deve estar entre 1 e 24 horas.", excecao.getMessage());
    }
}
