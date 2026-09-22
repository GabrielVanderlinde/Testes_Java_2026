package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClassificadorIdadeTest {

    @ParameterizedTest(name = "Idade {0} anos = {1}")
    @CsvSource({
            "0,   CRIANCA",
            "5,   CRIANCA",
            "12,  CRIANCA",
            "13,  ADOLESCENTE",
            "17,  ADOLESCENTE",
            "18,  ADULTO",
            "30,  ADULTO",
            "59,  ADULTO",
            "60,  IDOSO",
            "80,  IDOSO"
    })
    void classificarDeveRetornarCategoriaCorreta(
            int idade,
            String esperado) {

        String resultado = ClassificadorIdade.classificar(idade);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest(name = "Idade inválida: {0}")
    @ValueSource(ints = {-1, -5, -20, -100})
    void idadeNegativaDeveLancarExcecao(int idade) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> ClassificadorIdade.classificar(idade)
        );

        assertEquals("Idade não pode ser negativa.", excecao.getMessage());
    }
}
