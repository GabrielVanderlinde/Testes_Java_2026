package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidadorNumeroTest {

    @ParameterizedTest(name = "Número par: {0}")
    @ValueSource(ints = {2, 4, 10, 20, 100, 1000})
    void numerosParesDevemRetornarTrue(int numero) {
        assertTrue(ValidadorNumero.ehPar(numero));
    }

    @ParameterizedTest(name = "Número ímpar: {0}")
    @ValueSource(ints = {1, 3, 7, 15, 99, 101})
    void numerosImparesDevemRetornarFalse(int numero) {
        assertFalse(ValidadorNumero.ehPar(numero));
    }
}
