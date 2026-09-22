package exercicios.aula05.desafios;

public final class CalculadoraEstacionamento {

    private CalculadoraEstacionamento() {
    }

    public static double calcular(int horas, boolean fimDeSemana) {
        if (horas < 1 || horas > 24) {
            throw new IllegalArgumentException("A permanência deve estar entre 1 e 24 horas.");
        }

        double valorBase = 5.0 + ((horas - 1) * 3.0);

        if (fimDeSemana) {
            return valorBase * 1.2;
        }

        return valorBase;
    }
}
