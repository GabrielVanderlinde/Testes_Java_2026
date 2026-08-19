package entrega_atividades.Aula4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RetanguloTest {

    @Test
    void calcularAreaDeveMultiplicarLarguraPorAltura() {
        // Arrange
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        // Act
        double area = retangulo.calcularArea();

        // Assert
        assertEquals(20.0, area);
    }


    @Test
    void calcularPerimetroDeveSomarTodosOsLados() {
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        double perimetro = retangulo.calcularPerimetro();

        assertEquals(18.0, perimetro);
    }


    @Test
    void retanguloDeveTerTodosOsDadosCorretos() {
        // Arrange + Act
        Retangulo retangulo = new Retangulo(3.0, 6.0);

        // Assert: assertAll roda TODAS as verificações, mesmo que uma falhe
        assertAll(
                () -> assertEquals(3.0, retangulo.getLargura()),
                () -> assertEquals(6.0, retangulo.getAltura()),
                () -> assertEquals(18.0, retangulo.calcularArea())
        );
    }



    @Test
    void criarRetanguloComLarguraInvalidaDeveLancarExcecao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Retangulo(-1, 5.0)
        );
    }

    @Test
    void criarRetanguloComAlturaInvalidaDeveLancarExcecao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Retangulo(4.0, 0)
        );
    }
}
