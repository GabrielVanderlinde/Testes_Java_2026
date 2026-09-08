package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void usuarioRecemCriadoDeveTerTelefoneNulo(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        assertNull(usuario.getTelefone());
    }

    @Test
    void usuarioRecemCriadoDeveTerEstadoInicialCorreto() {
        // Arrange + Act
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Assert: todas as verificações devem ser executadas.
        assertAll(
                () -> assertEquals("paulo", usuario.getNome()),
                () -> assertEquals("paulo@gmail.com", usuario.getEmail()),
                () -> assertNull(usuario.getTelefone()),
                () -> assertTrue(usuario.isAtivo())
        );
    }

    @Test
    void verificarSeDepoisDefinidoOTelefoneNaoDeveSerNulo(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        usuario.definirTelefone("9999-9999");

        assertNotNull(usuario.getTelefone());
    }

    @Test
    void verificarSeTelefoneObtidoIgualInformado(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        usuario.definirTelefone("9999-9999");

        assertEquals("9999-9999",usuario.getTelefone());
    }

    @Test
    void telefoneNuloDeveLancarExcecao(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> usuario.definirTelefone(null)
        );

        assertEquals("O telefone é obrigatório.", excecao.getMessage());
    }

    @Test
    void telefoneEmBrancoDeveLancarExcecao(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> usuario.definirTelefone("")
        );

        assertEquals("O telefone é obrigatório.", excecao.getMessage());
    }

    @Test
    void deveAlterarEstadoParaInativo(){
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        usuario.desativar();

        assertFalse(usuario.isAtivo());
    }

}
