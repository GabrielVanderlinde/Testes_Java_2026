package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClassificadorNotaTest {

    @ParameterizedTest(name = "Nota {0} = {1}")
    @CsvSource({
            "0.0,  REPROVADO",
            "2.5,  REPROVADO",
            "4.9,  REPROVADO",
            "5.0,  RECUPERACAO",
            "6.0,  RECUPERACAO",
            "6.9,  RECUPERACAO",
            "7.0,  APROVADO",
            "8.5,  APROVADO",
            "10.0, APROVADO"
    })
    void classificarDeveRetornarSituacaoCorreta(
            double nota,
            String esperado) {

        String resultado = ClassificadorNota.classificar(nota);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest(name = "Nota negativa: {0}")
    @ValueSource(doubles = {-0.1, -1.0, -5.0, -10.0})
    void notaNegativaDeveLancarExcecao(double nota) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorNota.classificar(nota)
        );

        assertEquals("Nota deve estar entre 0 e 10.", excecao.getMessage());
    }

    @ParameterizedTest(name = "Nota acima de 10: {0}")
    @ValueSource(doubles = {10.1, 11.0, 15.0, 20.0})
    void notaAcimaDeDezDeveLancarExcecao(double nota) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorNota.classificar(nota)
        );

        assertEquals("Nota deve estar entre 0 e 10.", excecao.getMessage());
    }
}
