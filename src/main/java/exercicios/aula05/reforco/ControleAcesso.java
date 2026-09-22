package exercicios.aula05.reforco;

public final class ControleAcesso {

    private ControleAcesso() {
    }

    public static boolean podeEntrar(
            int idade,
            boolean acompanhado) {

        if (idade < 0) {
            throw new IllegalArgumentException(
                "Idade inválida."
            );
        }

        if (idade >= 18) {
            return true;
        }

        return idade >= 16 && acompanhado;
    }
}
