package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LampadaTest {

    @Test
    void verificaSeLampadaRecemCriadaEstaDesligada(){
        Lampada lampada = new Lampada();

        assertFalse(lampada.isLigada());
    }

    @Test
    void verificaSeLampadaRecemCriadaTemIntensidadeZero(){
        Lampada lampada = new Lampada();

        assertEquals(0,lampada.getIntensidade());
    }

    @Test
    void ligarDeveAlterarEstadoDaLampada() {
        // Arrange: crie uma lâmpada.
        Lampada lampada = new Lampada();

        // Act: ligue a lâmpada.
        lampada.ligar();

        // Assert: verifique o estado e a intensidade.
        assertTrue(lampada.isLigada());
    }

    @Test
    void ligarDeveAlterarIntensidadeDaLampada() {
        // Arrange: crie uma lâmpada.
        Lampada lampada = new Lampada();

        // Act: ligue a lâmpada.
        lampada.ligar();

        // Assert: verifique o estado e a intensidade.
        assertEquals(100,lampada.getIntensidade());
    }

    @Test
    void desligarDeveAlterarEstadoEIntensidade() {
        // Arrange: crie uma lâmpada.
        Lampada lampada = new Lampada();

        // Act: ligue a lâmpada.
        lampada.ligar();

        lampada.desligar();

        assertAll(
                () -> assertFalse(lampada.isLigada()),
                () -> assertEquals(0,lampada.getIntensidade())
        );
    }
}
