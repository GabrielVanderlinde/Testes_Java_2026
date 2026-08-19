package entrega_atividades.Aula4;

public class Retangulo {

    private double largura;
    private double altura;

    public Retangulo(double largura, double altura) {
        if (largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Largura e altura devem ser maiores que zero.");
        }

        this.largura = largura;
        this.altura = altura;
    }

    public double calcularArea() {
        return this.largura * this.altura;
    }

    public double calcularPerimetro() {
        return 2 * (this.largura + this.altura);
    }

    public double getLargura() {
        return largura;
    }

    public double getAltura() {
        return altura;
    }
}
