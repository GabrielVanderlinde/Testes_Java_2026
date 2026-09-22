package exercicios.aula05.reforco;

public final class ValidadorUsuario {

    private ValidadorUsuario() {
    }

    public static boolean nomeValido(String nome) {

        if (nome == null || nome.isBlank()) {
            return false;
        }

        return nome.length() >= 3;
    }
}
