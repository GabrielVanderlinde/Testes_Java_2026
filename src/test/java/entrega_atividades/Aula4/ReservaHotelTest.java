package entrega_atividades.Aula4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    void deveCalcularOValorTotalDaReservaCorretamente() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel("João Silva", 4, 150.0);

        // Act
        double valorTotal = reserva.calcularValorTotal();

        // Assert
        assertEquals(600.0, valorTotal);
    }

    @Test
    void deveRetornarFalsoQuandoAReservaForCancelada() {
        ReservaHotel reserva = new ReservaHotel("Maria Souza", 2, 200.0);
        assertTrue(reserva.isReservaAtiva());

        reserva.cancelarReserva();

        assertFalse(reserva.isReservaAtiva());
    }

    @Test
    void deveLancarExcecaoAoTentarCriarReservaComZeroDias() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Carlos", 0, 100.0)
        );
    }

    @Test
    void deveCriarReservaComTodosOsDadosCorretos() {
        ReservaHotel reserva = new ReservaHotel("Ana Paula", 5, 120.0);

        assertAll(
                () -> assertEquals("Ana Paula", reserva.getNomeHospede()),
                () -> assertEquals(5, reserva.getQuantidadeDias()),
                () -> assertEquals(120.0, reserva.getValorDiaria()),
                () -> assertTrue(reserva.isReservaAtiva())
        );
    }
}