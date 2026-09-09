package exercicios.aula04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetanguloTest {

    @Test
    void calcularAreaDeveMultiplicarLados() {
        // Arrange
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        // Act
        double area = retangulo.calcularArea();

        // Assert
        assertEquals(20.0, area);
    }


    @Test
    void calcularPerimetroDeveSomarTodosOsLados() {
        // Arrange
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        // Act
        double perimetro = retangulo.calcularPerimetro();

        // Assert
        assertEquals(18.0, perimetro);
    }


    @Test
    void retanguloDeveTerTodosOsDadosCorretos() {
        // Arrange
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        // Act
        double largura = retangulo.getLargura();
        double altura = retangulo.getAltura();

        // Assert
        assertEquals(4.0, largura);
        assertEquals(5.0, altura);
    }



    @Test
    void larguraInvalidaDeveLancarExcecao() {
        // Arrange: não há setup necessário
        // Act: tentar criar retângulo com largura inválida
        // Assert: verificar que lança exceção
        assertThrows(
                IllegalArgumentException.class,
                () -> new Retangulo(-1, 5.0)
        );
    }
}
