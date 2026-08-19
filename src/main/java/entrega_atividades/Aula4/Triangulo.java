package entrega_atividades.Aula4;

public class Triangulo {

    private double ladoA,ladoB,ladoC;

    public Triangulo(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Lados devem ser positivos.");

        this.ladoA = a; this.ladoB = b; this.ladoC = c;
    }

    public double calcularPerimetro() {
        return ladoA + ladoB + ladoC;
    }

    public double getLadoA() {
        return ladoA;
    }

    public double getLadoB() {
        return ladoB;
    }

    public double getLadoC() {
        return ladoC;
    }
}
