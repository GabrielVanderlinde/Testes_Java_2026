package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidadorUsuarioTest {

    @ParameterizedTest(name = "Nome válido: {0}")
    @ValueSource(strings = {"Ana", "Carlos", "Maria", "Joao123", "usuario"})
    void nomesValidosDevemRetornarTrue(String nome) {
        assertTrue(ValidadorUsuario.nomeValido(nome));
    }

    @ParameterizedTest(name = "Valor ausente: {0}")
    @NullAndEmptySource
    void valoresAusentesDevemRetornarFalse(String nome) {
        assertFalse(ValidadorUsuario.nomeValido(nome));
    }

    @ParameterizedTest(name = "Espaço: '{0}'")
    @ValueSource(strings = {" ", "  "})
    void espacosDevemRetornarFalse(String nome) {
        assertFalse(ValidadorUsuario.nomeValido(nome));
    }
}
