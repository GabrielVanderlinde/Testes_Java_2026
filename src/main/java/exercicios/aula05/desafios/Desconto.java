package exercicios.aula05.desafios;

public final class Desconto {

    private Desconto() { }

    public static double calcular(double preco, int percentual) {
        if (preco < 0) {
            throw new IllegalArgumentException(
                    "O preço não pode ser negativo."
            );
        }

        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException(
                    "O percentual deve estar entre 0 e 100."
            );
        }

        double valorDoDesconto = preco * percentual / 100.0;
        return preco - valorDoDesconto;
    }
}