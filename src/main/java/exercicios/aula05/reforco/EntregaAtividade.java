package exercicios.aula05.reforco;

public final class EntregaAtividade {

    private EntregaAtividade() {
    }

    public static String verificar(
            int minutosAtraso,
            boolean justificativa) {

        if (minutosAtraso < 0) {
            return "NO_PRAZO";
        }

        if (minutosAtraso == 0) {
            return "NO_PRAZO";
        }

        if (minutosAtraso <= 10) {
            return "ATRASO_TOLERADO";
        }

        if (justificativa) {
            return "ANALISE_PROFESSOR";
        }

        return "ATRASADA";
    }
}
