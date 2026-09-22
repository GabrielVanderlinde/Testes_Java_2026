package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstacionamentoTest {

    @ParameterizedTest(name = "{0} horas = R$ {1}")
    @CsvSource({
            "1,  5.0",
            "2,  10.0",
            "3,  10.0",
            "4,  15.0",
            "5,  15.0",
            "6,  15.0",
            "7,  25.0",
            "8,  25.0",
            "10, 25.0",
            "12, 25.0"
    })
    void calcularValorDeveCobrarTaxaCorreta(
            int horas,
            double esperado) {

        double resultado = Estacionamento.calcularValor(horas);
        assertEquals(esperado, resultado, 0.001);
    }

    @ParameterizedTest(name = "Tempo inválido: {0}")
    @ValueSource(ints = {0, -1, -5, -100})
    void tempoInvalidoDeveLancarExcecao(int horas) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> Estacionamento.calcularValor(horas)
        );

        assertEquals("Tempo inválido.", excecao.getMessage());
    }
}
