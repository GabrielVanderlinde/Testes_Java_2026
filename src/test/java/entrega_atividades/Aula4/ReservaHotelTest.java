package entrega_atividades.Aula4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    void calcularValorTotalDeveMultiplicarDiasPorDiaria() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel("Maycon Lucas", 4, 150.0);

        // Act
        double valorTotal = reserva.calcularValorTotal();

        // Assert
        assertEquals(600.0, valorTotal);
    }

    @Test
    void cancelarReservaDeveAlterarStatusParaInativo() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel("Maria Cecilia", 2, 200.0);
        assertTrue(reserva.isReservaAtiva());

        // Act
        reserva.cancelarReserva();

        // Assert
        assertFalse(reserva.isReservaAtiva());
    }

    @Test
    void criarReservaComDiasInvalidosDeveLancarExcecao() {
        // Act + Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("Maycon", 0, 100.0)
        );
    }

    @Test
    void reservaDeveTerTodosOsDadosCorretos() {
        // Arrange + Act
        ReservaHotel reserva = new ReservaHotel("Maria Cecilia", 5, 120.0);

        // Assert
        assertAll(
                () -> assertEquals("Maria Cecicilia", reserva.getNomeHospede()),
                () -> assertEquals(5, reserva.getQuantidadeDias()),
                () -> assertEquals(120.0, reserva.getValorDiaria()),
                () -> assertTrue(reserva.isReservaAtiva())
        );
    }
}