package exercicios.aula04.desafios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LampadaTest {

    @Test
    void verificaSeLampadaRecemCriadaEstaDesligada(){
        // Arrange: criar lâmpada
        Lampada lampada = new Lampada();

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar estado inicial desligado
        assertFalse(lampada.isLigada());
    }

    @Test
    void verificaSeLampadaRecemCriadaTemIntensidadeZero(){
        // Arrange: criar lâmpada
        Lampada lampada = new Lampada();

        // Act: não há ação necessária (estado inicial)
        // Assert: verificar intensidade inicial zero
        assertEquals(0,lampada.getIntensidade());
    }

    @Test
    void ligarDeveAlterarEstadoDaLampada() {
        // Arrange: criar lâmpada
        Lampada lampada = new Lampada();

        // Act: ligar a lâmpada
        lampada.ligar();

        // Assert: verificar estado ligado
        assertTrue(lampada.isLigada());
    }

    @Test
    void ligarDeveAlterarIntensidadeDaLampada() {
        // Arrange: criar lâmpada
        Lampada lampada = new Lampada();

        // Act: ligar a lâmpada
        lampada.ligar();

        // Assert: verificar intensidade alterada para 100
        assertEquals(100,lampada.getIntensidade());
    }

    @Test
    void desligarDeveAlterarEstadoEIntensidade() {
        // Arrange: criar e ligar lâmpada
        Lampada lampada = new Lampada();
        lampada.ligar();

        // Act: desligar a lâmpada
        lampada.desligar();

        // Assert: verificar estado e intensidade usando assertAll
        assertAll(
                () -> assertFalse(lampada.isLigada()),
                () -> assertEquals(0,lampada.getIntensidade())
        );
    }
}
