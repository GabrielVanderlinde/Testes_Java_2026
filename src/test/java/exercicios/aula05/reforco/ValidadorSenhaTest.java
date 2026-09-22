package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidadorSenhaTest {

    @ParameterizedTest(name = "Senha válida: {0}")
    @CsvSource({
            "Senha123,  true",
            "Teste2026, true",
            "Abcdefg1, true",
            "XyZ12345, true",
            "MinhaSenha99, true"
    })
    void senhasValidasDevemRetornarTrue(String senha, boolean esperado) {
        boolean resultado = ValidadorSenha.senhaValida(senha);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest(name = "Senha inválida: {0}")
    @CsvSource({
            "senha123,  false",
            "SENHAAAA, false",
            "Abc1,      false",
            "12345678,  false",
            "SemNumero, false",
            "semminuscula1, false",
            "SEMNUMERO123, true"
    })
    void senhasInvalidasDevemRetornarFalse(String senha, boolean esperado) {
        boolean resultado = ValidadorSenha.senhaValida(senha);
        assertEquals(esperado, resultado);
    }

    @ParameterizedTest(name = "Senha null deve retornar false")
    @NullSource
    void senhaNullDeveRetornarFalse(String senha) {
        assertFalse(ValidadorSenha.senhaValida(senha));
    }
}
