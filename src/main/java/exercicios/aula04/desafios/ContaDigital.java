package exercicios.aula04.desafios;

public class ContaDigital {

    private String titular;
    private double saldo;

    public ContaDigital(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public void depositar(double valor){
        if (valor<=0){
            throw new IllegalArgumentException("O depósito deve ser maior que zero.");
        }

        saldo+=valor;
    }

    public void sacar(double valor){
        if (valor<=0){
            throw new IllegalArgumentException("O saque deve ser maior que zero.");
        }

        if (valor>saldo){
            throw new IllegalStateException("Saldo insuficiente.");
        }

        saldo-=valor;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
