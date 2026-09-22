package exercicios.aula05.reforco;

public final class ConversorTemperatura {

    private ConversorTemperatura() {
    }

    public static double celsiusParaFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }
}
