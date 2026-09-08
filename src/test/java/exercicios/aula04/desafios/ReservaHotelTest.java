package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    void reservaRecemCriadaDeveTerDadosEEstadoInicialCorretos() {
        // Arrange: criar reserva
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar todos os dados iniciais usando assertAll
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
        // Arrange: criar reserva
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        // Act: calcular total
        double resultado = reserva.calcularTotal();

        // Assert: verificar cálculo correto com delta
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
        // Arrange & Assert: tentar criar reserva com hóspede nulo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel(null,2,2)
        );

        assertEquals("O hóspede é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void hospedeEmBrancoDeveLancarExcecao() {
        // Arrange & Assert: tentar criar reserva com hóspede em branco e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("",2,2)
        );

        assertEquals("O hóspede é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void quantidadeZeroDeveLancarExcecao() {
        // Arrange & Assert: tentar criar reserva com quantidade zero e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",0,2)
        );

        assertEquals("A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void quantidadeNegativaDeveLancarExcecao() {
        // Arrange & Assert: tentar criar reserva com quantidade negativa e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",-10,2)
        );

        assertEquals("A quantidade de diárias deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void valorZeroDeveLancarExcecao() {
        // Arrange & Assert: tentar criar reserva com valor zero e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",10,0.0)
        );

        assertEquals("O valor da diária deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void valorNegativoDeveLancarExcecao() {
        // Arrange & Assert: tentar criar reserva com valor negativo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel("paulo",10,-10.0)
        );

        assertEquals("O valor da diária deve ser maior que zero.",
                excecao.getMessage());
    }

    @Test
    void codigoNuloDeveLancarExcecao() {
        // Arrange: criar reserva
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        // Act & Assert: tentar confirmar com código nulo e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar(null)
        );

        assertEquals("O código de confirmação é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void codigoEmBrancoDeveLancarExcecao() {
        // Arrange: criar reserva
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);

        // Act & Assert: tentar confirmar com código em branco e verificar exceção e mensagem
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> reserva.confirmar("")
        );

        assertEquals("O código de confirmação é obrigatório.",
                excecao.getMessage());
    }

    @Test
    void confirmarDuasVezesDeveLancarExcecao() {
        // Arrange: criar e confirmar reserva
        ReservaHotel reserva = new ReservaHotel("Paulo",
                10,10);
        reserva.confirmar("AUDTT-1010");

        // Act & Assert: tentar confirmar novamente e verificar exceção e mensagem usando assertAll
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
