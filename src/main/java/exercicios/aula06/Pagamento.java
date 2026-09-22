package exercicios.aula06;

public abstract class Pagamento {
    protected double valor;

    public Pagamento(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public abstract double calcularTaxa();
}
