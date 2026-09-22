package exercicios.aula04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrianguloTest {

    @Test
    void calcularPerimetroDeveSomarOsTresLados() {
        // Arrange
        Triangulo triangulo = new Triangulo(3, 4, 5);
        // Act
        double perimetro = triangulo.calcularPerimetro();
        // Assert
        assertEquals(12.0, perimetro);
    }

    @Test
    void ladoNegativoDeveLancarExcecao() {
        // Arrange: não há setup necessário
        // Act: tentar criar triângulo com lado negativo
        // Assert: verificar que lança exceção
        assertThrows(
                IllegalArgumentException.class,
                () -> new Triangulo(-1, 4, 5)
        );
    }

    @Test
    void triangulo3_4_5DeveTerLadosCorretos() {
        // Arrange
        Triangulo t = new Triangulo(3, 4, 5);

        // Act
        double ladoA = t.getLadoA();
        double ladoB = t.getLadoB();
        double ladoC = t.getLadoC();

        // Assert
        assertAll(
                () -> assertEquals(3.0, ladoA),
                () -> assertEquals(4.0, ladoB),
                () -> assertEquals(5.0, ladoC)
        );
    }
}
