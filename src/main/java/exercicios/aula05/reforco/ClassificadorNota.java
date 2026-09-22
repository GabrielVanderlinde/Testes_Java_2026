package exercicios.aula05.reforco;

public final class ClassificadorNota {

    private ClassificadorNota() {
    }

    public static String classificar(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException(
                "Nota deve estar entre 0 e 10."
            );
        }

        if (nota <= 4.9)
            return "REPROVADO";

        if (nota <= 6.9)
            return "RECUPERACAO";

        return "APROVADO";
    }
}
