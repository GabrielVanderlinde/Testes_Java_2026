package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    void reservaRecemCriadaDeveTerDadosEEstadoInicialCorretos() {
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        assertAll(
                () -> assertEquals("Paulo",reserva.getHospede()),
                () -> assertEquals(10,reserva.getQuantidadeDiarias()),
                () -> assertEquals(10,reserva.getValorDiaria()),
                () -> assertFalse(reserva.isConfirmada()),
                () -> assertNull(reserva.getCodigoConfirmacao())
        );
    }

    @Test
    void calcularTotalDeveMultiplicarDiariasPeloValor() {
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        double resultado = reserva.calcularTotal();

        assertEquals(100,resultado,0.001);
    }

    @Test
    void confirmarDeveAlterarEstadoEArmazenarCodigo() {
        // Arrange
        ReservaHotel reserva = new ReservaHotel(
                "Carla Souza",
                2,
                300.0
        );

        // Act
        reserva.confirmar("RES-2026-001");

        // Assert
        assertAll(
                () -> assertTrue(reserva.isConfirmada()),
                () -> assertNotNull(
                        reserva.getCodigoConfirmacao()
                ),
                () -> assertEquals(
                        "RES-2026-001",
                        reserva.getCodigoConfirmacao()
                )
        );
    }

    @Test
    void hospedeNuloDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel(null,2,2)
        );

        assertEquals("O hóspede é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void hospedeEmBrancoDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("",2,2)
        );

        assertEquals("O hóspede é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void quantidadeZeroDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",0,2)
        );

        assertEquals("A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void quantidadeNegativaDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",-10,2)
        );

        assertEquals("A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void valorZeroDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",10,0.0)
        );

        assertEquals("O valor da diária deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void valorNegativoDeveLancarExcecao() {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",10,-10.0)
        );

        assertEquals("O valor da diária deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void codigoNuloDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar(null)
        );

        assertEquals("O código de confirmação é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void codigoEmBrancoDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar("")
        );

        assertEquals("O código de confirmação é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void confirmarDuasVezesDeveLancarExcecao() {
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        reserva.confirmar("AUDTT-1010");

        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> reserva.confirmar("TTT-1010")
        );

        assertAll(
                () -> assertEquals("A reserva já está confirmada.",
                excecao.getMessage()),
                        () -> assertEquals("AUDTT-1010",
                                reserva.getCodigoConfirmacao())
        );
    }
}
