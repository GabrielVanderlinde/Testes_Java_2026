package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void usuarioRecemCriadoDeveTerTelefoneNulo(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar telefone nulo
        assertNull(usuario.getTelefone());
    }

    @Test
    void usuarioRecemCriadoDeveTerEstadoInicialCorreto() {
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar todos os campos iniciais usando assertAll
        assertAll(
                () -> assertEquals("paulo", usuario.getNome()),
                () -> assertEquals("paulo@gmail.com", usuario.getEmail()),
                () -> assertNull(usuario.getTelefone()),
                () -> assertTrue(usuario.isAtivo())
        );
    }

    @Test
    void verificarSeDepoisDefinidoOTelefoneNaoDeveSerNulo(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act: definir telefone
        usuario.definirTelefone("9999-9999");

        // Assert: verificar telefone não nulo
        assertNotNull(usuario.getTelefone());
    }

    @Test
    void verificarSeTelefoneObtidoIgualInformado(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act: definir telefone
        usuario.definirTelefone("9999-9999");

        // Assert: verificar telefone definido corretamente
        assertEquals("9999-9999",usuario.getTelefone());
    }

    @Test
    void telefoneNuloDeveLancarExcecao(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act & Assert: tentar definir telefone nulo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> usuario.definirTelefone(null)
        );

        assertEquals("O telefone é obrigatório.", excecao.getMessage());
    }

    @Test
    void telefoneEmBrancoDeveLancarExcecao(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act & Assert: tentar definir telefone em branco e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> usuario.definirTelefone("")
        );

        assertEquals("O telefone é obrigatório.", excecao.getMessage());
    }

    @Test
    void deveAlterarEstadoParaInativo(){
        // Arrange: criar usuário
        Usuario usuario = new Usuario("paulo","paulo@gmail.com");

        // Act: desativar usuário
        usuario.desativar();

        // Assert: verificar estado inativo
        assertFalse(usuario.isAtivo());
    }

}
