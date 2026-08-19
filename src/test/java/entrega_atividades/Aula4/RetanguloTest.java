package entrega_atividades.Aula4;

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
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        double perimetro = retangulo.calcularPerimetro();

        assertEquals(18.0, perimetro);
    }


    @Test
    void retanguloDeveTerTodosOsDadosCorretos() {
        Retangulo retangulo = new Retangulo(4.0, 5.0);

        double largura = retangulo.getLargura();
        double altura = retangulo.getAltura();

        assertEquals(4.0, largura);
        assertEquals(5.0, altura);
    }



    @Test
    void larguraInvalidaDeveLancarExcecao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Retangulo(-1, 5.0)
        );
    }
}
