package exercicios.aula05.reforco;

public final class Autenticador {

    private Autenticador() {
    }

    public static boolean autenticar(
            String usuario,
            String senha,
            boolean ativo) {

        if (usuario == null || senha == null) {
            return false;
        }

        return usuario.equals("admin")
                && senha.equals("Senai123")
                && ativo;
    }
}
