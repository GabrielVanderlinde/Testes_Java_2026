package exercicios.aula04.desafios;

public class ReservaHotel {

   private String hospede;
   private int quantidadeDiarias;
   private double valorDiaria;
   private boolean confirmada;
   private String codigoConfirmacao;

    public ReservaHotel(String hospede, int quantidadeDiarias, double valorDiaria) {
        if (hospede == null || hospede.isBlank()){
            throw new IllegalArgumentException("O hóspede é obrigatório.");
        }

        if (quantidadeDiarias<=0){
            throw new IllegalArgumentException("A quantidade de diárias deve ser maior que zero.");
        }

        if (valorDiaria<=0){
            throw new IllegalArgumentException("O valor da diária deve ser maior que zero.");
        }

        this.hospede = hospede;
        this.quantidadeDiarias = quantidadeDiarias;
        this.valorDiaria = valorDiaria;
        this.confirmada = false;
        this.codigoConfirmacao = null;
    }

    public double calcularTotal(){
        return quantidadeDiarias*valorDiaria;
    }

    public void confirmar(String codigo){
        if (codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("O código de confirmação é obrigatório.");
        }

        if (confirmada){
            throw new IllegalStateException("A reserva já está confirmada.");
        }

        confirmada = true;
        codigoConfirmacao = codigo;
    }

    public String getHospede() {
        return hospede;
    }

    public int getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public String getCodigoConfirmacao() {
        return codigoConfirmacao;
    }
}
