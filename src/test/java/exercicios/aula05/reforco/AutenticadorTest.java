package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutenticadorTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("cenariosDeAutenticacao")
    void autenticarDeveVerificarCredenciais(
            String descricao,
            String usuario,
            String senha,
            boolean ativo,
            boolean esperado) {

        boolean resultado = Autenticador.autenticar(usuario, senha, ativo);
        assertEquals(esperado, resultado, descricao);
    }

    static Stream<Arguments> cenariosDeAutenticacao() {
        return Stream.of(
                Arguments.of("usuário correto + senha correta + ativo", "admin", "Senai123", true, true),
                Arguments.of("usuário correto + senha errada + ativo", "admin", "senhaErrada", true, false),
                Arguments.of("usuário errado + senha correta + ativo", "usuario", "Senai123", true, false),
                Arguments.of("usuário correto + senha correta + inativo", "admin", "Senai123", false, false),
                Arguments.of("usuário null + senha correta + ativo", null, "Senai123", true, false),
                Arguments.of("usuário correto + senha null + ativo", "admin", null, true, false),
                Arguments.of("usuário null + senha null + ativo", null, null, true, false),
                Arguments.of("usuário errado + senha errada + ativo", "user", "pass", true, false)
        );
    }
}
