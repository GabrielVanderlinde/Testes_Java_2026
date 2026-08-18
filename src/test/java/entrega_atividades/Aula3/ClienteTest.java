package entrega_atividades.Aula3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void configurar(){
        cliente = new Cliente("paulo","paulo@gmail.com");
    }

    @Test
    public void testarObjetoDeCliente(){
        Cliente cliente = new Cliente("tiago","tiago@gmail.com");

        assertEquals("tiago",cliente.getNome());
        assertEquals("tiago@gmail.com",cliente.getEmail());
    }

    @Test
    public void clienteDeveEstarAtivoAssimQueCriado(){
        assertTrue(cliente.isAtivo());
    }

    @Test
    public void testarSeClienteVaiSerDesativado(){
        cliente.desativar();

        assertFalse(cliente.isAtivo());
    }
}
