package entrega_atividades.Aula3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    public void configurar(){
        pedido = new Pedido();
    }

    @Test
    public void pedidoDeveNascerComValorZero() {
        assertEquals(0.0, pedido.getValorTotal());
    }

    @Test
    public void adicionarItemDeveSomarAoTotal() {
        pedido.adicionarItem(50.0);
        pedido.adicionarItem(30.0);
        assertEquals(80.0, pedido.getValorTotal());
    }
}
