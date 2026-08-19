package entrega_atividades.Aula4;

public class ReservaHotel {
    private String nomeHospede;
    private int quantidadeDias;
    private double valorDiaria;
    private boolean reservaAtiva;

    public ReservaHotel(String nomeHospede, int quantidadeDias, double valorDiaria) {
        if (quantidadeDias <= 0 || valorDiaria <= 0) {
            throw new IllegalArgumentException("A quantidade de dias e o valor da diária devem ser maiores que zero.");
        }

        this.nomeHospede = nomeHospede;
        this.quantidadeDias = quantidadeDias;
        this.valorDiaria = valorDiaria;
        this.reservaAtiva = true;
    }

    public double calcularValorTotal() {
        return this.quantidadeDias * this.valorDiaria;
    }

    public void cancelarReserva() {
        this.reservaAtiva = false;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public boolean isReservaAtiva() {
        return reservaAtiva;
    }
}