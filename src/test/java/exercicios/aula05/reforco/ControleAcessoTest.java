package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControleAcessoTest {

    @ParameterizedTest(name = "Idade {0}, Acompanhado: {1} = {2}")
    @CsvSource({
            "15, true,  false",
            "15, false, false",
            "16, true,  true",
            "16, false, false",
            "17, true,  true",
            "17, false, false",
            "18, true,  true",
            "18, false, true",
            "25, false, true"
    })
    void podeEntrarDeveVerificarRegraDeAcesso(
            int idade,
            boolean acompanhado,
            boolean esperado) {

        boolean resultado = ControleAcesso.podeEntrar(idade, acompanhado);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest(name = "Idade inválida: {0}")
    @CsvSource({
            "-1, true",
            "-5, false",
            "-10, true"
    })
    void idadeNegativaDeveLancarExcecao(
            int idade,
            boolean acompanhado) {

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ControleAcesso.podeEntrar(idade, acompanhado)
        );

        assertEquals("Idade inválida.", excecao.getMessage());
    }
}
