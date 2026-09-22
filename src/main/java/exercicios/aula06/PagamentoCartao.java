package exercicios.aula06;

public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return valor * 0.025;
    }
}
