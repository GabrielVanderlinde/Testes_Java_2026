package exercicios.aula05.reforco;

public final class ClassificadorIdade {

    private ClassificadorIdade() {
    }

    public static String classificar(int idade) {

        if (idade < 0) {
            throw new IllegalArgumentException(
                "Idade não pode ser negativa."
            );
        }

        if (idade <= 12)
            return "CRIANCA";

        if (idade <= 17)
            return "ADOLESCENTE";

        if (idade <= 59)
            return "ADULTO";

        return "IDOSO";
    }
}
