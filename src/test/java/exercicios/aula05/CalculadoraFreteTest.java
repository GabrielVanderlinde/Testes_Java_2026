package exercicios.aula05;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraFreteTest {

    @ParameterizedTest(name = "Peso {0}kg, Expresso: {1} = R$ {2}")
    @CsvSource({
            "0.01, false,  8.02",
            "1.00, false, 10.00",
            "1.00, true,  15.00",
            "5.00, false, 18.00",
            "10.00, true, 42.00"
    })
    void deveCalcularValorDoFreteCorretamente(
            double pesoKg,
            boolean entregaExpressa,
            double esperado
    ){
        double obtido = CalculadoraFrete.calcular(pesoKg, entregaExpressa);

        assertEquals(esperado,obtido, 0.001);
    }

    @ParameterizedTest(name = "Peso inválido {0}kg deve ser rejeitado")
    @ValueSource(doubles = {0.0, -0.01, -1.0, -5.5})
    void pesoZeroOuNegativoDeveLancarExcecao(double pesoKg) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraFrete.calcular(pesoKg, false)
        );

        assertEquals(
                "O peso deve ser maior que zero.",
                excecao.getMessage()
        );
    }
}
