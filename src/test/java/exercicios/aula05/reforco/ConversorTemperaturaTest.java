package exercicios.aula05.reforco;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversorTemperaturaTest {

    @ParameterizedTest(name = "{0}°C = {1}°F")
    @CsvSource({
            "0,    32",
            "10,   50",
            "20,   68",
            "30,   86",
            "100,  212",
            "-10,  14",
            "-40,  -40"
    })
    void celsiusParaFahrenheitDeveConverterCorretamente(
            double celsius,
            double esperado) {

        double resultado = ConversorTemperatura.celsiusParaFahrenheit(celsius);
        assertEquals(esperado, resultado, 0.001);
    }
}
